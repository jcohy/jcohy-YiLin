package com.jcohy.yilin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class YiLinApplication {

	public static void main(String[] args) {
		SpringApplication.run(YiLinApplication.class, args);
	}

	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}

}
