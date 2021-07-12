package com.example.demo.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import com.example.demo.modules.sys.entity.User;
import com.example.demo.modules.sys.mapper.UserMapper;
import com.example.demo.modules.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 员工信息 服务实现类
 * </p>
 *
 * @author luox
 * @since 2021-07-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseEntity<List<User>> getUser(QueryEntity<User> queryEntity){
        Page<User> page = new Page<>();
        page.setCurrent(queryEntity.getCurrent());
        page.setSize(queryEntity.getSize());

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        List<User> list = userMapper.selectPage(page,queryWrapper).getRecords();
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,list);
    }

    @Override
    public ResponseEntity<List<User>> getUser2(QueryEntity<User> queryEntity) throws Exception{
        Page<User> page = new Page<>();
        page.setCurrent(queryEntity.getCurrent());
        page.setSize(queryEntity.getSize());
        QueryWrapper queryWrapper = new QueryWrapper();
//        QueryWrapper queryWrapper2 = Wrappers.query();

//        ===============
        User user = queryEntity.getEntity();

//        Class<User> userClass = User.class;
        Class userClass = User.class;

        List fieldsList = new ArrayList<Field>();
        while (userClass != null) {  // 遍历所有父类字节码对象
            Field[] declaredFields = userClass.getDeclaredFields();
            fieldsList.addAll(Arrays.asList(declaredFields));  //将`Filed[]`数组转换为`List<>`然后再将其拼接至`ArrayList`上

            userClass = userClass.getSuperclass();  // 获得父类的字节码对象
        }

        for (Object field : fieldsList) {  // 打印当前类以及其父类的多有属性对象
            System.out.println(field);
        }


        Field[] fields = userClass.getDeclaredFields();
        for(Field field : fields){
            if(field.getName().equals("serialVersionUID")) continue;


            Object v = invokeMethod(user, field.getName());
            //sex 为字符串，数据库枚举型需要int
            if(field.getName().equals("sex")){
                v = Integer.parseInt(v.toString());
            }
            System.out.println(field.getName() + "\t" + v + "\t" + field.getType());

            if(v != null){
                queryWrapper.eq(humpToLine2(field.getName()),v);
            }
        }

        return new ResponseEntity<>(ResponseEntity.OK,"success",userMapper.selectPage(page,queryWrapper).getRecords());
//        return userMapper.selectList(null);

    }

    private static Object invokeMethod(Object owner, String methodName) throws Exception {
        Class ownerClass = owner.getClass();
        methodName = methodName.substring(0, 1).toUpperCase()
                + methodName.substring(1);
        Method method = ownerClass.getMethod("get" + methodName);
        return method.invoke(owner);
    }

    /** 驼峰转下划线*/
    private static String humpToLine2(String str) {
        Pattern humpPattern = Pattern.compile("[A-Z]");
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    @Override
    public List<User> getUserBySex(Integer sex) {
//        return userMapper.selectList(new QueryWrapper<User>().eq("sex",sex));
//        QueryWrapper queryWrapper = new QueryWrapper<User>().eq("sex",sex);
//        return list(queryWrapper);
        return userMapper.getUserBySex(sex);
    }

    @Override
    public ResponseEntity<Boolean>saveUser(List<User> list) {
        boolean result = saveBatch(list);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }

    @Override
    public ResponseEntity<Boolean> updateUser(List<User> list) {
        boolean result = updateBatchById(list);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }

    @Override
    public ResponseEntity<Boolean> deleteUser(List<Long> idList) {
        boolean result = removeByIds(idList);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }
}
