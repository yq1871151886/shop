package com.fh;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {
        public static void main(String[] args) {
            // 代码生成器
            AutoGenerator mpg = new AutoGenerator();

            // 全局配置
            GlobalConfig gc = new GlobalConfig();
            final String projectPath = System.getProperty("user.dir");
            gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("杨奇宫");/**《==== 作者名*/
            gc.setOpen(false);
            mpg.setGlobalConfig(gc);

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig();
            dsc.setUrl("jdbc:mysql://localhost:3306/commodity?useUnicode=tru e&characterEncoding=UTF-8&serverTimezone=UTC");
            // dsc.setSchemaName("public");
            dsc.setDriverName("com.mysql.jdbc.Driver");
            dsc.setUsername("root");
            dsc.setPassword("admin");
            mpg.setDataSource(dsc);

            // 包配置
            PackageConfig pc = new PackageConfig();
            //自定义模块名
            final String moduleName="fh";
            pc.setEntity("bean");
            pc.setMapper("dao");
            pc.setModuleName(moduleName);
            pc.setParent("com");/*《==== 包名（自己手动设置）*/
            mpg.setPackageInfo(pc);

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            };
            List<FileOutConfig> focList = new ArrayList();
            focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输入文件名称================================模块名（自己设置）
                    return projectPath + "/src/main/resources/mapper/" + moduleName
                            + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);
            mpg.setTemplate(new TemplateConfig().setXml(null));

            // 策略配置**（个性化定制）**
            StrategyConfig strategy = new StrategyConfig();
            strategy.setNaming(NamingStrategy.underline_to_camel);
            strategy.setColumnNaming(NamingStrategy.underline_to_camel);

            //实体类自动继承Entity,不需要也可以
          /*  strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");*/
            //小辣椒默认为true
            strategy.setEntityLombokModel(false);
            strategy.setRestControllerStyle(true);
            //控制层自动继承父类BaseController,不需要也可以
          /*  strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");*/
            //设置要被扫描的表名
            strategy.setInclude("t_shop_addressee");
            strategy.setSuperEntityColumns("id");
            strategy.setControllerMappingHyphenStyle(true);
            strategy.setTablePrefix(pc.getModuleName() + "_");
            mpg.setStrategy(strategy);
            mpg.setTemplateEngine(new FreemarkerTemplateEngine());
            mpg.execute();
        }

}
