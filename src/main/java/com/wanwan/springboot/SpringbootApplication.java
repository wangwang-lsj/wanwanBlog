package com.wanwan.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        log.info("==================项目启动了==================");

        SpringApplication.run(SpringbootApplication.class, args);
        log.info("==================项目启动成功了===============");
    }

}
