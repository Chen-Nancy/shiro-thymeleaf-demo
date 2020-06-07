package com.nancy.shirothymeleafdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.nancy.shirothymeleafdemo.mapper")
public class ShiroThymeleafDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroThymeleafDemoApplication.class, args);
    }

}
