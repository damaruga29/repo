package com.ava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Avaa1Application {

	public static void main(String[] args) {
		SpringApplication.run(Avaa1Application.class, args);
	}

}
