package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
    
    @Autowired
    private ${table.mapperName} ${table.mapperName?uncap_first};

    @Override
    public ResponseEntity<List<${entity}>> get${entity}(QueryEntity<${entity}> queryEntity){
        Page<${entity}> page = new Page<>();
        page.setCurrent(queryEntity.getCurrent());
        page.setSize(queryEntity.getSize());

        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();

        List<${entity}> list = ${table.mapperName?uncap_first}.selectPage(page,queryWrapper).getRecords();
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,list);
    }
    
    @Override
    public ResponseEntity<Boolean> save${entity}(List<${entity}> list) {
        boolean result = saveBatch(list);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }

    @Override
    public ResponseEntity<Boolean> update${entity}(List<${entity}> list) {
        boolean result = updateBatchById(list);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }

    @Override
    public ResponseEntity<Boolean> delete${entity}(List<Integer> idList) {
        boolean result = removeByIds(idList);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }
    
}
</#if>
