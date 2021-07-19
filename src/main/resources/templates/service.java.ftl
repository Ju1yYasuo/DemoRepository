package com.example.demo.service.${package.ModuleName};

import com.example.demo.util.entity.QueryEntity;
import com.example.demo.util.entity.ResponseEntity;
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
     * @param queryEntity 查询实体
     * @return {@link ResponseEntity<List<${entity}>> }
     * @author ${author}
     * @date ${date}
     */
    ResponseEntity<List<${entity}>> get${entity}(QueryEntity<${entity}> queryEntity);

    /**
     * 保存${table.comment}
     *
     * @param ${entity?uncap_first} ${table.comment}
     * @return {@link ResponseEntity<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    ResponseEntity<Boolean> save${entity}(${entity} ${entity?uncap_first});

    /**
     * 更新${table.comment}
     *
     * @param ${entity?uncap_first} ${table.comment}
     * @return {@link ResponseEntity<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    ResponseEntity<Boolean> update${entity}(${entity} ${entity?uncap_first});

    /**
     * 删除${table.comment}
     *
     * @param idList id列表
     * @return {@link ResponseEntity<Boolean> }
     * @author ${author}
     * @date ${date}
     */
    ResponseEntity<Boolean> delete${entity}(List<Integer> idList);

}
</#if>
