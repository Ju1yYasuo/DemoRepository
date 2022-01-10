package com.example.demo.service.sys.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.config.exception.MyException;
import com.example.demo.entity.sys.User;
import com.example.demo.mapper.sys.UserMapper;
import com.example.demo.service.sys.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.common.Constant;
import com.example.demo.util.document.PoiUtil;
import com.example.demo.util.entity.QueryResultEntity;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 人员信息 服务实现类
 * </p>
 *
 * @author luox
 * @since 2021-07-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public QueryResultEntity<List<User>> getUser(Page<User> page, String fuzzySearch){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        QueryResultEntity<List<User>> queryResultEntity= new QueryResultEntity<>();
        List<User> list;
        if(page != null){
            list = page(page,queryWrapper).getRecords();
            int total = count(queryWrapper);
            queryResultEntity.setTotal(total);
        }else{
            list = list(queryWrapper);
        }
        queryResultEntity.setData(list);
        return queryResultEntity;
    }
    
    @Override
    public boolean saveUser(User user) {
        return save(user);
    }

    @Override
    public boolean updateUser(User user) {
        return updateById(user);
    }

    @Override
    public boolean deleteUser(List<Integer> idList) {
        return removeByIds(idList);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String,Object> login(String userName, String password) {
        //获取用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("user_name",userName);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            throw new MyException("用户名不存在");
        }
        //验证状态
        Date currentTime = new Date();
        if(currentTime.getTime() >= user.getAccountExpirationDate().getTime()){
            throw new MyException("账号已到期，请联系管理员");
        }
        if(StpUtil.isDisable(user.getId())){
            int remainingTime = (int) Math.ceil((StpUtil.getDisableTime(user.getId()) - currentTime.getTime()) / (1000f * 60f));
            if(remainingTime > 0){
                throw new MyException("此账号已锁定，锁定剩余时长" + remainingTime + "分钟");
            }
        }
        //sa会自动解封/24h后 人性化清除错误次数
        boolean whetherAfter24h = (user.getLogLastTime() != null && (currentTime.getTime() - user.getLogLastTime().getTime()) > (1000 * 60 * 60 * 24));
        if (user.getLogErrorNumber() >= 5 || whetherAfter24h) {
            user.setLogErrorNumber(0);
        }

        //验证密码
        if(!user.getPassword().equals(DigestUtil.md5Hex(password))){
            //登录失败
            int logErrorNumber = user.getLogErrorNumber() + 1;
            if(logErrorNumber >= 5){
                user.setLogStatus(2);
                StpUtil.logoutByLoginId(user.getId());
                StpUtil.disable(user.getId(),60 * 5);
            }

            user.setLogErrorNumber(logErrorNumber);
            user.setLogLastTime(currentTime);
            updateById(user);
            throw new MyException("密码错误" + logErrorNumber + "次");
        }
        //是否第一次登录，要求强制修改密码
        boolean whetherFirstLog = false;
        if(user.getLogLastTime() == null){
            whetherFirstLog = true;
        }
        //session过时自动验证，重复登陆时踢人下线并登录，框架已配置不允许重复登录
        //登录成功
        StpUtil.login(user.getId());
        user.setLogStatus(1);
        user.setLogErrorNumber(0);
        user.setLogLastTime(currentTime);
        updateById(user);
        //获取角色
        //List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id",user.getId()));
        //List<Integer> roles = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        //获取权限
        //List<Integer> permissions = userMapper.getUserPermissions(user.getId());
        //获取token
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();

        Map<String,Object> map = new HashMap<>(4);
        map.put("user",user);
        //map.put("roles",roles);
        //map.put("permissions",permissions);
        //前台传入参数名必须是 spring.application.name + session gkptsession
        map.put(saTokenInfo.getTokenName(),saTokenInfo.getTokenValue());
        if(whetherFirstLog){
            map.put("whetherFirstLog", true);
        }
        return map;
    }

    @Override
    public boolean logOut() {
        int id = StpUtil.getLoginIdAsInt();
        User user = getById(id);
        user.setLogStatus(0);
        //根据token注销
        StpUtil.logout();
        return updateById(user);
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword) {
        int id = StpUtil.getLoginIdAsInt();
        User user = getById(id);
        if(!user.getPassword().equals(DigestUtil.md5Hex(oldPassword))){
            throw new MyException("原始密码错误");
        }
        user.setPassword(DigestUtil.md5Hex(newPassword));
        return updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean importExcel(MultipartFile file) throws IOException {
        Workbook workbook;
        InputStream inputStream = new BufferedInputStream(file.getInputStream());
        if(Objects.requireNonNull(file.getOriginalFilename()).endsWith(Constant.XLSX_FILE_TYPE)){
            workbook = new XSSFWorkbook(inputStream);
        }else{
            workbook = new HSSFWorkbook(inputStream);
        }
        inputStream.close();

        Sheet sheet = workbook.getSheetAt(0);
        Row titleRow = sheet.getRow(0);
        List<Integer> titleIndexList = new ArrayList<>();
        for (String title : Constant.USER_UPLOAD_TITLE) {
            for(int i = 0;i < Constant.USER_UPLOAD_TITLE.length;i++){
                if(title.equals(titleRow.getCell(i).getStringCellValue())){
                    titleIndexList.add(i);
                    break;
                }
            }
        }
        if(titleIndexList.size() != Constant.USER_UPLOAD_TITLE.length){
            throw new MyException("excel标题不正确，正确标题为：" + Arrays.toString(Constant.USER_UPLOAD_TITLE));
        }

        List<User> saveList = new ArrayList<>();
        //获取部门映射数据
        //Map<String,Integer> departmentMap = departmentService.getDepartmentMapNameKey();
        //获取字典映射数据(value可能相同时须分开查询)
        List<String> typeList = new ArrayList<>();
        typeList.add("sys_whether");
        typeList.add("sys_user_position");
        typeList.add("sys_work_type");
        typeList.add("sys_job_level");
        typeList.add("sys_helmet_type");
        //Map<String,Integer> dictionaryMap = dictionaryService.getDictionaryMapNameKey(typeList);

        for(int i = 1,rows = sheet.getPhysicalNumberOfRows();i < rows; i++){
            try{
                Row row = sheet.getRow(i);
                User user = new User();
                user.setUserName(PoiUtil.getStringValue(row.getCell(titleIndexList.get(0))));
                user.setName(PoiUtil.getStringValue(row.getCell(titleIndexList.get(1))));
                user.setSex(PoiUtil.getStringValue(row.getCell(titleIndexList.get(2))));
                user.setAge(PoiUtil.getIntegerValue(row.getCell(titleIndexList.get(3))));
                user.setCardNumber(PoiUtil.getStringValue(row.getCell(titleIndexList.get(4))));
                //user.setDepartment(departmentMap.get(PoiUtil.getStringValue(row.getCell(titleIndexList.get(5)))));
                //user.setPosition(dictionaryMap.get(PoiUtil.getStringValue(row.getCell(titleIndexList.get(6)))));
                //user.setWorkType(dictionaryMap.get(PoiUtil.getStringValue(row.getCell(titleIndexList.get(7)))));
                user.setJobCode(PoiUtil.getStringValue(row.getCell(titleIndexList.get(8))));
                //user.setJobLevel(dictionaryMap.get(PoiUtil.getStringValue(row.getCell(titleIndexList.get(9)))));
                //user.setHelmetType(dictionaryMap.get(PoiUtil.getStringValue(row.getCell(titleIndexList.get(10)))));
                user.setBoundHelmet(PoiUtil.getStringValue(row.getCell(titleIndexList.get(11))));
                user.setUserNumber(PoiUtil.getStringValue(row.getCell(titleIndexList.get(12))));
                //user.setWhetherScreen(dictionaryMap.get(PoiUtil.getStringValue(row.getCell(titleIndexList.get(13)))));
                user.setPhoneNumber(PoiUtil.getStringValue(row.getCell(titleIndexList.get(14)),true));
                user.setIdNumber(PoiUtil.getStringValue(row.getCell(titleIndexList.get(15)),true));
                user.setEmergencyContact(PoiUtil.getStringValue(row.getCell(titleIndexList.get(16)),true));
                user.setEmergencyContactPhone(PoiUtil.getStringValue(row.getCell(titleIndexList.get(17)),true));
                saveList.add(user);
                if(saveList.size() == 1000){
                    saveBatch(saveList);
                    saveList.clear();
                }
            }catch (Exception e){
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                throw new MyException("第" + (i + 1) + "行，数据错误." + e.getMessage());
            }
        }
        saveBatch(saveList);
        workbook.close();
        return true;
    }

    @Override
    public XSSFWorkbook exportExcel(List<User> list) throws IOException {
        InputStream inputStream = new ClassPathResource("documentTemplates/人员信息导入导出模板V1.0.xlsx").getInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        inputStream.close();

        Sheet sheet = workbook.getSheetAt(0);
        short defaultHeight = sheet.getRow(0).getHeight();
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //获取部门字典映射数据
        //Map<Integer,String> departmentMap = departmentService.getDepartmentMapyIdKey();
        //获取字典映射数据
        List<String> typeList = new ArrayList<>();
        typeList.add("sys_whether");
        typeList.add("sys_user_position");
        typeList.add("sys_work_type");
        typeList.add("sys_job_level");
        typeList.add("sys_helmet_type");
        //Map<Integer,String> dictionaryMap = dictionaryService.getDictionaryMapIdKey(typeList);

        for(int i = 0,size = list.size();i < size;i++){
            User user = list.get(i);
            Row row = sheet.createRow(i + 1);
            row.setHeight(defaultHeight);
            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getUserName());

            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getName());

            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getSex());

            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getAge());

            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getCardNumber());

            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            //cell.setCellValue(departmentMap.get(user.getDepartment()));

            cell = row.createCell(6);
            cell.setCellStyle(cellStyle);
            //cell.setCellValue(dictionaryMap.get(user.getPosition()));

            cell = row.createCell(7);
            cell.setCellStyle(cellStyle);
            //cell.setCellValue(dictionaryMap.get(user.getWorkType()));

            cell = row.createCell(8);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getJobCode());

            cell = row.createCell(9);
            cell.setCellStyle(cellStyle);
            //cell.setCellValue(dictionaryMap.get(user.getJobLevel()));

            cell = row.createCell(10);
            cell.setCellStyle(cellStyle);
            //cell.setCellValue(dictionaryMap.get(user.getHelmetType()));

            cell = row.createCell(11);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getBoundHelmet());

            cell = row.createCell(12);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getUserNumber());

            cell = row.createCell(13);
            cell.setCellStyle(cellStyle);
            //cell.setCellValue(dictionaryMap.get(user.getWhetherScreen()));

            cell = row.createCell(14);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getPhoneNumber());

            cell = row.createCell(15);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getIdNumber());

            cell = row.createCell(16);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getEmergencyContact());

            cell = row.createCell(17);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(user.getEmergencyContactPhone());

            cell = row.createCell(18);
            cell.setCellStyle(cellStyle);
            //cell.setCellValue(DateKit.format(user.getAccountExpirationDate(),DateKit.DATETIME_PATTERN));
            cell.setCellValue(DateUtil.formatDate(user.getAccountExpirationDate()));

            cell = row.createCell(19);
            cell.setCellStyle(cellStyle);
            //cell.setCellValue(DateKit.format(user.getLogLastTime(),DateKit.DATETIME_PATTERN));
            cell.setCellValue(DateUtil.formatDate(user.getLogLastTime()));
        }
        return workbook;
    }


}
