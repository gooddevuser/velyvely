package com.velyvely;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;

@SpringBootApplication(
	exclude = { HttpEncodingAutoConfiguration.class }
)
public class VelyvelyApplication {	

	public static void main(String[] args) {
		SpringApplication.run(VelyvelyApplication.class, args);
	}

}
