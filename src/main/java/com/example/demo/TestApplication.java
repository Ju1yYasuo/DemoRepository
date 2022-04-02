package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * demo
 *
 * @author luox
 * @date 2021/06/24
 */
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@ComponentScan("com.example.demo")
@EnableCaching
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
