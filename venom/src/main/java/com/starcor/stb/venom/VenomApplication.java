package com.starcor.stb.venom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//新增数据库关联注解
@SpringBootApplication(scanBasePackages = {"com.starcor.stb"})
@EnableScheduling
@EnableTransactionManagement
public class VenomApplication {

    public static void main(String[] args) {
        SpringApplication.run(VenomApplication.class, args);
    }
}
