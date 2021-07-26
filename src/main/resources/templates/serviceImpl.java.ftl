package com.example.demo.service.${package.ModuleName}.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public List<${entity}> get${entity}(Page<${entity}> page, String fuzzySearch){
        List<${entity}> list;

        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();

        if(page != null){
            list = page(page,queryWrapper).getRecords();
        }else{
            list = list(queryWrapper);
        }
        return list;
    }
    
    @Override
    public Boolean save${entity}(${entity} ${entity?uncap_first}) {
        return save(${entity?uncap_first});
    }

    @Override
    public Boolean update${entity}(${entity} ${entity?uncap_first}) {
        return updateById(${entity?uncap_first});
    }

    @Override
    public Boolean delete${entity}(List<Integer> idList) {
        return removeByIds(idList);
    }
    
}
</#if>
