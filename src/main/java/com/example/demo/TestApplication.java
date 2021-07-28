package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 测试应用程序
 *
 * @author luox
 * @date 2021/06/24
 */
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
@ComponentScan("com.example.demo")
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}

}
