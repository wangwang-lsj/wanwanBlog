package com.wanwan.springboot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.springframework.beans.factory.annotation.Value;


import java.util.Collections;

/**
 * @Author：玩玩
 * @Date：2024/1/21 19:50
 * @Description:
 */
public class CodeGeneratorUtil {
    @Value("${server.ip}")
    static private String serverIp;
    public static void main(String[] args) {
        generate();
    }
    private static void generate(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/wanwan?serverTimezone=GMT%2b8", "root", "root")
                .globalConfig(builder -> {
                    builder.author("wanwan") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride()
                            .outputDir("D:\\IdeaProjects\\小白做毕设2024\\springboot\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wanwan.springboot") // 设置父包名
                            .moduleName(null) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\IdeaProjects\\小白做毕设2024\\springboot\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("") // 设置需要生成的表名
                            .addTablePrefix("t_", "sys_"); // 设置过滤表前缀
                    builder.entityBuilder()
                            .enableLombok();
                    builder.controllerBuilder()
                            .enableHyphenStyle()
                            .enableRestStyle();
                    builder.mapperBuilder()
                            .enableMapperAnnotation();
                })
                // .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
