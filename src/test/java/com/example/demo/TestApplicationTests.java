package com.example.demo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TestApplicationTests {

//	@Value只有在springboot配置注解下的配置类才可以使用
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Test
	public void autoGenerator(){

		AutoGenerator mpg = new AutoGenerator();

		// Step2：全局配置
		GlobalConfig gc = new GlobalConfig();
		// 填写代码生成的目录(需要修改)
		String projectPath = System.getProperty("user.dir");
//		String projectPath = "J:\\code\\demo";
		// 拼接出代码最终输出的目录
		gc.setOutputDir(projectPath + "/src/main/java");
		// 配置开发者信息（可选）（需要修改）
		gc.setAuthor("luox");
		// 配置是否打开目录，false 为不打开（可选）
		gc.setOpen(false);
		// 实体属性 Swagger2 注解，添加 Swagger 依赖，开启 Swagger2 模式（可选）
		//gc.setSwagger2(true);
		// 重新生成文件时是否覆盖，false 表示不覆盖（可选）
		gc.setFileOverride(false);
		// 配置主键生成策略，此处为 ASSIGN_ID（可选）
		gc.setIdType(IdType.ASSIGN_ID);
		// 配置日期类型，此处为 ONLY_DATE（可选）
		gc.setDateType(DateType.ONLY_DATE);
		// 默认生成的 service 会有 I 前缀
		gc.setServiceName("%sService");
		mpg.setGlobalConfig(gc);

		// Step3：数据源配置（需要修改）
		DataSourceConfig dsc = new DataSourceConfig();
		// 配置数据库 url 地址
		dsc.setUrl(url);
//		dsc.setUrl("jdbc:mysql://172.16.2.11:3306/bim_center_dev?useUnicode=true&characterEncoding=utf8");
		// dsc.setSchemaName("bim_center_dev"); // 可以直接在 url 中指定数据库名
		// 配置数据库驱动
		dsc.setDriverName(driverClassName);
		// 配置数据库连接用户名
		dsc.setUsername(username);
		// 配置数据库连接密码
		dsc.setPassword(password);
		mpg.setDataSource(dsc);

		// Step:4：包配置
		PackageConfig pc = new PackageConfig();
		// 配置父包名（需要修改）
		pc.setParent("com.example.demo");
		// 配置模块名（需要修改）
		pc.setModuleName("test");
		// 配置 entity 包名
		pc.setEntity("entity");
		// 配置 mapper 包名
		pc.setMapper("mapper");
		// 配置 service 包名
		pc.setService("service");
		// 配置 controller 包名
		pc.setController("controller");
		mpg.setPackageInfo(pc);

		//Step:5 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};

		// 如果模板引擎是 freemarker
		String templatePath = "/templates/mapper.xml.ftl";
		// 如果模板引擎是 velocity
		// String templatePath = "/templates/mapper.xml.vm";

		// 自定义输出配置
		List<FileOutConfig> focList = new ArrayList<>();
		// 自定义配置会被优先输出
		focList.add(new FileOutConfig(templatePath) {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
				return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
			}
		});
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());

		//Step6: 模板配置
		TemplateConfig templateConfig = new TemplateConfig();

		// 配置自定义输出模板
		//指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
		// templateConfig.setEntity("templates/entity2.java");
		// templateConfig.setService();
		// templateConfig.setController();

		templateConfig.setXml(null);
		mpg.setTemplate(templateConfig);

		// Step7：策略配置（数据库表配置）
		StrategyConfig strategy = new StrategyConfig();
		// 指定表名（可以同时操作多个表，使用 , 隔开）（需要修改）
		strategy.setInclude("luoxtest");
		// 配置数据表与实体类名之间映射的策略
		strategy.setNaming(NamingStrategy.underline_to_camel);
		// 配置数据表的字段与实体类的属性名之间映射的策略
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
		// 配置 lombok 模式
		strategy.setEntityLombokModel(true);
		// 配置 rest 风格的控制器（@RestController）
		strategy.setRestControllerStyle(true);
		// 配置驼峰转连字符
		strategy.setControllerMappingHyphenStyle(true);
		// 配置表前缀，生成实体时去除表前缀
		// 此处的表名为 test_mybatis_plus_user，模块名为 test_mybatis_plus，去除前缀后剩下为 user。
		strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);

		// Step6：执行代码生成操作
		mpg.execute();

	}

	//自定义模板引擎，继承类AbstractTemplateEngine
//	@Override
//	public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
//
//	}
//
//	@Override
//	public String templateFilePath(String filePath) {
//		return null;
//	}
}
