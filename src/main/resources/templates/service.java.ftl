package ${package.Service};

import com.example.demo.common.entity.QueryEntity;
import com.example.demo.common.entity.ResponseEntity;
import ${package.Entity}.${entity};
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

    ResponseEntity<List<${entity}>> get${entity}(QueryEntity<${entity}> queryEntity);

    ResponseEntity<Boolean> save${entity}(List<${entity}> list);

    ResponseEntity<Boolean> update${entity}(List<${entity}> list);

    ResponseEntity<Boolean> delete${entity}(List<Long> idList);

}
</#if>
