package com.pagadala;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringIntegrationRedisApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringIntegrationRedisApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);
	}

}
