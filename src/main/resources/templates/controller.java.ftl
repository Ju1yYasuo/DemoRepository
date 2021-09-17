package com.example.demo.controller.${package.ModuleName};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.core.json.JsonResult;
import com.example.demo.util.entity.CommonQueryDto;
import com.example.demo.entity.${package.ModuleName}.${entity};
import com.example.demo.service.${package.ModuleName}.${table.serviceName};
import com.example.demo.util.entity.QueryResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.*;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import java.util.List;

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
     * 获取${table.comment}
     *
     * @param commonQueryDto 通用查询dto
     * @return {@link JsonResult<QueryResultEntity<List<${entity}>>> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/get${entity}")
    public JsonResult<QueryResultEntity<List<${entity}>>> get${entity}(@RequestBody CommonQueryDto dto) {
        Page<${entity}> page = null;
        if(dto.getCurrent() != null && dto.getSize() != null){
            page = new Page<>();
            page.setCurrent(dto.getCurrent());
            page.setSize(dto.getSize());
        }
        return JsonResult.success(${table.serviceName?uncap_first}.get${entity}(page,dto.getFuzzySearch()));
    }

    /**
     * 保存${table.comment}
     *
     * @param ${entity?uncap_first} ${table.comment}
     * @return {@link JsonResult<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/save${entity}")
    public JsonResult<Boolean> save${entity}(@RequestBody ${entity} ${entity?uncap_first}){
        return JsonResult.success(${table.serviceName?uncap_first}.save${entity}(${entity?uncap_first}));
    }

    /**
     * 更新${table.comment}
     *
     * @param ${entity?uncap_first} ${table.comment}
     * @return {@link JsonResult<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/update${entity}")
    public JsonResult<Boolean> update${entity}(@RequestBody ${entity} ${entity?uncap_first}){
        return JsonResult.success(${table.serviceName?uncap_first}.update${entity}(${entity?uncap_first}));
    }

    /**
     * 删除${table.comment}
     *
     * @param idList id列表
     * @return {@link JsonResult<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/delete${entity}")
    public JsonResult<Boolean> delete${entity}(@RequestBody List<Integer> idList){
        return JsonResult.success(${table.serviceName?uncap_first}.delete${entity}(idList));
    }

}
</#if>
