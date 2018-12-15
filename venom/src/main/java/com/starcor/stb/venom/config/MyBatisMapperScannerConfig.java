package com.starcor.stb.venom.config;

/**
 * Created by chongyang.gao on 2018/12/15.
 */
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyBatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");

        //这个包名是所有的Mapper.java文件所在的路径，该包下面的子包里面的文件同样会扫描到。
        mapperScannerConfigurer.setBasePackage("com.starcor.stb.venom.mapper");
        return mapperScannerConfigurer;
    }

}
