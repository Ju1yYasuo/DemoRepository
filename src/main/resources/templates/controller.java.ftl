package com.example.demo.controller.${package.ModuleName};

import com.example.demo.utils.entity.QueryEntity;
import com.example.demo.utils.entity.ResponseEntity;
import com.example.demo.entity.${package.ModuleName}.${entity};
import com.example.demo.service.${package.ModuleName}.${table.serviceName};
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
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
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
     * @param queryEntity 查询实体
     * @return {@link ResponseEntity<List<${entity}>> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/get${entity}")
    public ResponseEntity<List<${entity}>> get${entity}(@RequestBody QueryEntity<${entity}> queryEntity) {
        return ${table.serviceName?uncap_first}.get${entity}(queryEntity);
    }

    /**
     * 保存${table.comment}
     *
     * @param ${entity?uncap_first} ${table.comment}
     * @return {@link ResponseEntity<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/save${entity}")
    public ResponseEntity<Boolean> save${entity}(@RequestBody ${entity} ${entity?uncap_first}){
        return ${table.serviceName?uncap_first}.save${entity}(${entity?uncap_first});
    }

    /**
     * 更新${table.comment}
     *
     * @param ${entity?uncap_first} ${table.comment}
     * @return {@link ResponseEntity<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/update${entity}")
    public ResponseEntity<Boolean> update${entity}(@RequestBody ${entity} ${entity?uncap_first}){
        return ${table.serviceName?uncap_first}.update${entity}(${entity?uncap_first});
    }

    /**
     * 删除${table.comment}
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/delete${entity}")
    public ResponseEntity<Boolean> delete${entity}(@RequestBody List<Integer> idList){
        return ${table.serviceName?uncap_first}.delete${entity}(idList);
    }

}
</#if>
