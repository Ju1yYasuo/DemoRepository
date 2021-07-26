package com.example.demo.service.${package.ModuleName};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.${package.ModuleName}.${entity};
import ${superServiceClassPackage};

import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 获取${table.comment}
     *
     * @param page        页面
     * @param fuzzySearch 模糊搜索
     * @return {@link List<${entity}> }
     * @author ${author}
     * @date ${date}
     */
    List<${entity}> get${entity}(Page<${entity}> page, String fuzzySearch);

    /**
     * 保存${table.comment}
     *
     * @param ${entity?uncap_first} ${table.comment}
     * @return {@link Boolean }
     * @author ${author}
     * @date ${date}
     */
    Boolean save${entity}(${entity} ${entity?uncap_first});

    /**
     * 更新${table.comment}
     *
     * @param ${entity?uncap_first} ${table.comment}
     * @return {@link Boolean }
     * @author ${author}
     * @date ${date}
     */
    Boolean update${entity}(${entity} ${entity?uncap_first});

    /**
     * 删除${table.comment}
     *
     * @param idList id列表
     * @return {@link Boolean }
     * @author ${author}
     * @date ${date}
     */
    Boolean delete${entity}(List<Integer> idList);

}
</#if>
