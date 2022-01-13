package ${package.Controller};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.core.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import com.example.demo.util.vo.BaseQueryVo;
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
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen?replace("-","_")}<#else>${table.entityPath}</#if>")
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
     * @return {@link JsonResult<${entity}> }
     * @author ${author}
     * @date ${date}
     */
    @GetMapping("/get/{id}")
    public JsonResult<${entity}> getById(@PathVariable("id") String id){
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(id);
        return JsonResult.success(${table.entityPath});
    }

    /**
     * 分页查询${table.comment}
     *
     * @param pageVo 分页vo
     * @param ${table.entityPath} ${table.comment}
     * @return {@link JsonResult<Page<${entity}>> }
     * @author ${author}
     * @date ${date}
     */
    @RequestMapping("/page")
    public JsonResult<Page<${entity}>> page(BaseQueryVo<${entity}> pageVo, ${entity} ${table.entityPath}) {
        return JsonResult.success(${table.entityPath}Service.page(pageVo, ${table.entityPath}));
    }

    /**
     * 保存${table.comment}
     *
     * @param ${table.entityPath} ${table.comment}
     * @return {@link JsonResult<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/save")
    public JsonResult<Boolean> save(@RequestBody ${entity} ${table.entityPath}){
        return JsonResult.info(${table.entityPath}Service.save(${table.entityPath}));
    }

    /**
     * 更新${table.comment}
     *
     * @param ${table.entityPath} ${table.comment}
     * @return {@link JsonResult<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/update")
    public JsonResult<Boolean> update(@RequestBody ${entity} ${table.entityPath}){
        return JsonResult.info(${table.entityPath}Service.updateById(${table.entityPath}));
    }

    /**
     * 删除${table.comment}
     *
     * @param idList id列表
     * @return {@link JsonResult<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    @PostMapping("/delete")
    public JsonResult<Boolean> delete(@RequestBody List<Long> idList){
        return JsonResult.info(${table.entityPath}Service.removeByIds(idList));
    }

}
