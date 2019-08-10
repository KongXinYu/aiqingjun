package com.aiqingjun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.aiqingjun.mapper")
@SpringBootApplication
public class AiqingjunApplication {
	public static void main(String[] args) {
		SpringApplication.run(AiqingjunApplication.class, args);
	}
}
