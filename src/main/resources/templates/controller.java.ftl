package ${package.Controller};

import com.example.demo.config.annotation.ResponseEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import com.example.demo.util.vo.BaseBodyVo;
import com.example.demo.util.vo.BaseQueryVo;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.*;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

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
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen?replace("-","_")}<#else>${table.entityPath}</#if>")
@ResponseEntity
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.entityPath}Service;

    /**
     * 根据id查询${table.comment}详情
     *
     * @param id id
     * @return {@link ${entity} }
     * @author ${author}
     * @date ${date}
     */
    @GetMapping("/get/{id}")
    public ${entity} getById(@PathVariable("id") Long id){
        return ${table.entityPath}Service.getById(id);
    }

    /**
     * 分页查询${table.comment}
     *
     * @param pageVo 分页vo
     * @param ${table.entityPath} ${table.comment}
     * @return {@link Page<${entity}> }
     * @author ${author}
     * @date ${date}
     */
    @GetMapping("/page")
    public Page<${entity}> page(BaseQueryVo<${entity}> pageVo, ${entity} ${table.entityPath}) {
        return ${table.entityPath}Service.page(pageVo, ${table.entityPath});
    }

    /**
     * 保存${table.comment}
     *
     * @param ${table.entityPath} ${table.comment}
     * @return {@link Boolean }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/save")
    public Boolean save(@RequestBody ${entity} ${table.entityPath}){
        return ${table.entityPath}Service.save(${table.entityPath});
    }

    /**
     * 更新${table.comment}
     *
     * @param ${table.entityPath} ${table.comment}
     * @return {@link Boolean }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/update")
    public Boolean update(@RequestBody ${entity} ${table.entityPath}){
        return ${table.entityPath}Service.updateById(${table.entityPath});
    }

    /**
     * 删除${table.comment}
     *
     * @param baseBodyVo 请求body基础vo
     * @return {@link Boolean }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/delete")
    public Boolean delete(@RequestBody @Validated BaseBodyVo baseBodyVo){
        return ${table.entityPath}Service.removeByIds(baseBodyVo.getIds());
    }

}
