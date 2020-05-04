package com.indi.cacheserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.indi.cacheserver.controller.InitialController;

@SpringBootApplication
public class CacheServerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CacheServerApplication.class, args);
		context.getBean(InitialController.class).initialProcess();
	}

}
