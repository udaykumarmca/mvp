package com.doehrs;

import com.doehrs.auth.FeignClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(excludeFilters={
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value= FeignClientConfiguration.class)})
public class SecoundApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecoundApplication.class, args);
    }
}
