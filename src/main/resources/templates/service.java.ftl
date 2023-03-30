package ${package.Service};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.example.demo.util.vo.BaseQueryVo;

/**
 * ${table.comment!} 服务类
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

    /**
     * 分页查询${table.comment}
     *
     * @param pageVo 分页vo
     * @param ${table.entityPath} ${table.comment}
     * @return {@link Page<${entity}> }
     * @author ${author}
     * @date ${date}
     */
    Page<${entity}> page(BaseQueryVo<${entity}> pageVo, ${entity} ${table.entityPath});
}
