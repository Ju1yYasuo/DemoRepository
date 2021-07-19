package com.example.demo.service.${package.ModuleName}.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.util.entity.QueryEntity;
import com.example.demo.util.entity.ResponseEntity;
import com.example.demo.entity.${package.ModuleName}.${entity};
import com.example.demo.mapper.${package.ModuleName}.${table.mapperName};
import com.example.demo.service.${package.ModuleName}.${table.serviceName};
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

        List<${entity}> list = page(page,queryWrapper).getRecords();
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,list);
    }
    
    @Override
    public ResponseEntity<Boolean> save${entity}(${entity} ${entity?uncap_first}) {
        boolean result = save(${entity?uncap_first});
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }

    @Override
    public ResponseEntity<Boolean> update${entity}(${entity} ${entity?uncap_first}) {
        boolean result = updateById(${entity?uncap_first});
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }

    @Override
    public ResponseEntity<Boolean> delete${entity}(List<Integer> idList) {
        boolean result = removeByIds(idList);
        return new ResponseEntity<>(ResponseEntity.OK,ResponseEntity.success,result);
    }
    
}
</#if>
