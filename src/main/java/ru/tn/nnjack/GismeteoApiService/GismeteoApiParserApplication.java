package ru.tn.nnjack.GismeteoApiService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
public class GismeteoApiParserApplication {

	public static void main(String[] args) {

		ApplicationContext context =SpringApplication.run(GismeteoApiParserApplication.class, args);
	}

}
