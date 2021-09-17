package com.example.demo.service.${package.ModuleName}.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.${package.ModuleName}.${entity};
import com.example.demo.mapper.${package.ModuleName}.${table.mapperName};
import com.example.demo.service.${package.ModuleName}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.example.demo.util.entity.QueryResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ${table.comment!} 服务实现类
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
    public QueryResultEntity<List<${entity}>> get${entity}(Page<${entity}> page, String fuzzySearch){
        LambdaQueryWrapper<${entity}> queryWrapper = new LambdaQueryWrapper<>();

        QueryResultEntity<List<${entity}>> queryResultEntity = new QueryResultEntity<>();
        List<${entity}> list;
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
    public boolean save${entity}(${entity} ${entity?uncap_first}) {
        return save(${entity?uncap_first});
    }

    @Override
    public boolean update${entity}(${entity} ${entity?uncap_first}) {
        return updateById(${entity?uncap_first});
    }

    @Override
    public boolean delete${entity}(List<Integer> idList) {
        return removeByIds(idList);
    }
    
}
</#if>
