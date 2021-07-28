package com.example.demo.controller.${package.ModuleName};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.util.entity.ResponseEntity;
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
import java.util.Map;

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
     * @param map map
     * @return {@link ResponseEntity<List<${entity}>> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/get${entity}")
    public ResponseEntity<List<${entity}>> get${entity}(@RequestBody Map<String,Object> map) {
            Page<${entity}> page = null;
            String fuzzySearch = null;
            if(map.containsKey("current") && map.containsKey("size")){
                page = new Page<>();
                page.setCurrent(Long.parseLong(String.valueOf(map.get("current"))));
                page.setSize(Long.parseLong(String.valueOf(map.get("size"))));
            }
            if(map.containsKey("fuzzySearch")){
                fuzzySearch = (String) map.get("fuzzySearch");
            }
            return ResponseEntity.success(${table.serviceName?uncap_first}.get${entity}(page,fuzzySearch));
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
        return ResponseEntity.success(${table.serviceName?uncap_first}.save${entity}(${entity?uncap_first}));
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
        return ResponseEntity.success(${table.serviceName?uncap_first}.update${entity}(${entity?uncap_first}));
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
        return ResponseEntity.success(${table.serviceName?uncap_first}.delete${entity}(idList));
    }

}
</#if>
