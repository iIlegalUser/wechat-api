package com.example.user.config;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

/**
 * 配置文档：<a href="https://baomidou.com/pages/981406/">...</a>
 */
public class CodeGenerator {
    private static final String modulePath = "/user";
    private static final String parentPackage = "com.example.user";


    // 数据源配置
    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(
            "jdbc:mysql://42.192.40.227:3306/compound?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8",
            "root",
            "766248235")
            .build();

    /**
     * 读取控制台内容
     * @param tip 输入提示
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 项目绝对路径，如 F:\Program Files (x86)\IntelliJ IDEA 2021.3\Projects\api
        String projectPath = System.getProperty("user.dir");
        // 模块地址,可以在加在输出文件路径里(projectPath + modulePath + "/src/main/java")生成不同模块的文件
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(DATA_SOURCE_CONFIG);
        mpg.global(globalConfig(projectPath, modulePath))
                .strategy(strategyConfig())
                .packageInfo(packageConfig())
                .template(templateConfig())
                // Freemarker引擎或者Velocity引擎
                .execute(new FreemarkerTemplateEngine());
    }

    /**
     * 全局配置
     * @param projectPath 项目地址
     * @param modulePath 模块地址
     */
    public static GlobalConfig globalConfig(String projectPath, String modulePath) {
        return new GlobalConfig.Builder()
                // 作者
                .author("sail")
                // 执行完是否打开输出的目录，默认true
                .disableOpenDir()
                // 设置输出文件路径
                .outputDir(projectPath + modulePath + "/src/main/java")
                // 覆盖已有的文件，默认false(第一次生成时放开)
//                .fileOverride()
                // 开启swagger模式
//                .enableSwagger()
                // 设置日期类型为Date(若不设置时间类型都会变成LocalDateTime部分连接池例如druid是无法识别的)
                .dateType(DateType.ONLY_DATE)
                .build();
    }

    /**
     * 包配置
     */
    public static PackageConfig packageConfig() {
        return new PackageConfig.Builder()
                // 模块名称
                //.moduleName(scanner("模块名"))
                // 父包名
                .parent(parentPackage)
                // 自定义实体包名(不同的模块自己手动修改)
                .entity("com/example/user/entity")
                // 自定义mapper包名(不同的模块自己手动修改)
                .mapper("com/example/user/mapper")
                // 自定义mapper.xml包名(不同的模块自己手动修改)
                .xml("mapper.xml")
                // 自定义service包名(不同的模块自己手动修改)
                .service("com/example/user/service")
                // 自定义serviceImpl包名(不同的模块自己手动修改)
                .serviceImpl("service.impl")
                // 自定义controller包名(不同的模块自己手动修改)
                .controller("com/example/user/controller")
                .build();
    }

    /**
     * 策略配置
     */
    public static StrategyConfig strategyConfig() {
        return new StrategyConfig.Builder()
                .addInclude(scanner("表名，多个英文逗号分割").split(","))
//                .addTablePrefix("t_")   // 增加过滤表前缀
                .entityBuilder()
                .enableTableFieldAnnotation()   // 开启生成实体时生成字段注解
                .enableLombok()
                .enableFileOverride()
                .controllerBuilder()
                .enableRestStyle()  // 开启生成@RestController控制器
                .enableHyphenStyle()    // 开启驼峰转连字符
                .mapperBuilder()
                .build();
    }

    /**
     * 配置自定义输出模板
     */
    public static TemplateConfig templateConfig() {
        return new TemplateConfig.Builder()
                .entity("templates/entity.java")
                .service("/templates/service.java")
                .controller("/templates/controller.java")
                .mapper("/templates/mapper.java")
                .xml("/templates/mapper.xml")
                .controller("/templates/controller.java")
                .build();
    }

}
