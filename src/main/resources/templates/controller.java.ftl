package ${package.Controller};

import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
     * @param list 列表
     * @return {@link ResponseEntity<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/save${entity}")
    @Transactional
    public ResponseEntity<Boolean> save${entity}(@RequestBody List<${entity}> list){
        return ${table.serviceName?uncap_first}.save${entity}(list);
    }

    /**
     * 更新${table.comment}
     *
     * @param list 列表
     * @return {@link ResponseEntity<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PutMapping("/update${entity}")
    @Transactional
    public ResponseEntity<Boolean> update${entity}(@RequestBody List<${entity}> list){
        return ${table.serviceName?uncap_first}.update${entity}(list);
    }

    /**
     * 删除${table.comment}
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @DeleteMapping("/delete${entity}")
    @Transactional
    public ResponseEntity<Boolean> delete${entity}(@RequestBody List<Long> idList){
        return ${table.serviceName?uncap_first}.delete${entity}(idList);
    }

}
</#if>
