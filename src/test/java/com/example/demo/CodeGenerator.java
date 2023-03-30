package com.example.demo;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.demo.config.exception.MyException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 *
 * @author luox
 * @date 2021/6/25
 */
@Data
public class CodeGenerator {
    /*开发者*/
    private String author = "AutoGenerator";
    /*子工程，多模块项目使用*/
    private String project = "";
    /*功能模块，弃用此规范*/
    private String moduleName = "";
    /*顶级包*/
    private String topPackage = "org";
    /*表*/
    private String[] tables = null;
    /*表前缀*/
    private String tablePrefix = "";
    //数据库
    private String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    private String username = "root";
    private String password = "julyyasuo";
    private String driver = "com.mysql.cj.jdbc.Driver";

    public enum Strategy {
        FULL,
        ID,
        NORMAL
    }

    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator();
        generator.setAuthor("luox");
        //generator.setProject("demo-web");
        //generator.setModuleName("bi");
        //generator.setTablePrefix("bi_");
        generator.setTopPackage("com.example.demo");
        generator.setTables(new String[]{"cl_detail"});
        generator.codeGenerator(Strategy.NORMAL);
    }

    public void codeGenerator(Strategy fullStrategy) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        //生成代码到指定模块
        if (StrUtil.isBlank(project)) {
            gc.setOutputDir(projectPath + "/src/main/java");
        } else {
            gc.setOutputDir(projectPath + "/" + project + "/src/main/java");
        }
        gc.setAuthor(author);
        gc.setOpen(false);
        // 重新生成文件时是否覆盖，false 表示不覆盖（可选）
        gc.setFileOverride(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        // 配置日期类型，此处为 ONLY_DATE（可选）
        gc.setDateType(DateType.ONLY_DATE);
        // 默认生成的 service 会有 I 前缀
        gc.setServiceName("%sService");
//        mapper 配置
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driver);
        // 配置数据库连接用户名t
        dsc.setUsername(username);
        // 配置数据库连接密码
        dsc.setPassword(password);
        // 配置字段生成风格
        dsc.setTypeConvert((globalConfig, fieldType) -> {
            String t = fieldType.toLowerCase();
            if (t.contains("tinyint")) {
                return DbColumnType.INTEGER;
            }
            //其它字段采用默认转换（非mysql数据库可以使用其它默认的数据库转换器）
            return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
        });
        //dsc.setTypeConvert(new MySqlTypeConvertCustom());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(topPackage);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String xmlTemplatePath = "templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义文件路径配置会被优先输出
        focList.add(new FileOutConfig(xmlTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String path;
                if (StrUtil.isBlank(project)) {
                    path = projectPath + "/src/main/resources/mapper/";
                } else {
                    path = projectPath + "/" + project + "/src/main/resources/mapper/";
                }
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return path + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*focList.add(new FileOutConfig(entityTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/org/dahuici/gkpt/entity/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig(controllerTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/org/dahuici/gkpt/controller/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig(mapperTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/org/dahuici/gkpt/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig(serviceTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/org/dahuici/gkpt/service/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });
        focList.add(new FileOutConfig(serviceImplTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/org/dahuici/gkpt/service/" + pc.getModuleName()
                        + "/impl/" + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
            }
        });*/

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setMapper("templates/mapper.java");
        templateConfig.setService("templates/service.java");
        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setController("templates/controller.java");
        //取消设置xml，上面已自定义配置xml自定义模板输出路径,xml路径默认在mapper中，故不使用
        templateConfig.setXml(null);
        //templateConfig.setXml("templates/mapper.xml");
        mpg.setTemplate(templateConfig);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        if (tables == null || tables.length == 0) {
            throw new MyException("没有表");
        }
        strategy.setInclude(tables);


        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(tablePrefix);

        if (Strategy.FULL.equals(fullStrategy)) {
            fullBaseEntity(strategy);
        } else if (Strategy.ID.equals(fullStrategy)) {
            idEntity(strategy);
        }
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * FullBaseEntity作为父类时
     *
     * @param strategy 策略
     * @author luox
     * @date 2022/05/18
     */
    private static void fullBaseEntity(StrategyConfig strategy) {
        List<TableFill> tableFillList = new ArrayList<>();
        strategy.setSuperEntityClass("com.example.demo.util.entity.FullBaseEntity");
        String[] baseColumns = {"id", "deleted", "remark", "create_time", "create_by", "update_time", "update_by", "update_update"};
        strategy.setLogicDeleteFieldName("deleted");
        tableFillList.add(new TableFill("deleted", FieldFill.INSERT));
        tableFillList.add(new TableFill("create_by", FieldFill.INSERT));
        tableFillList.add(new TableFill("create_time", FieldFill.INSERT));
        tableFillList.add(new TableFill("update_by", FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("update_time", FieldFill.INSERT_UPDATE));
        strategy.setSuperEntityColumns(baseColumns);
        strategy.setTableFillList(tableFillList);
    }

    /**
     * IdEntity作为父类时
     *
     * @param strategy 策略
     * @author luox
     * @date 2022/05/18
     */
    private static void idEntity(StrategyConfig strategy) {
        List<TableFill> tableFillList = new ArrayList<>();
        strategy.setSuperEntityClass("com.example.demo.util.entity.IdEntity");
        String[] baseColumns = {"id"};
        strategy.setSuperEntityColumns(baseColumns);
        strategy.setTableFillList(tableFillList);
    }
}