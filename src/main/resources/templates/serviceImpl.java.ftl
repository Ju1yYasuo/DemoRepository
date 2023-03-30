package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.example.demo.util.vo.BaseQueryVo;
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public Page<${entity}> page(BaseQueryVo<${entity}> pageVo, ${entity} ${table.entityPath}){
        LambdaQueryWrapper<${entity}> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.setEntity(${table.entityPath});
        return page(pageVo.toPage(), queryWrapper);
    }
    
}
