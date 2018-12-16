package com.starcor.stb.venom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

//新增数据库关联注解
@SpringBootApplication(scanBasePackages = {"com.starcor.stb"}, exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.starcor.stb.venom.mapper")
@ServletComponentScan
public class VenomApplication {

    public static void main(String[] args) {
        SpringApplication.run(VenomApplication.class, args);
    }
}
