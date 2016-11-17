package com.charitable.charity.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;

@SpringBootApplication
public class CharityCrudApplication {

	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
		MongoTemplate template = new MongoTemplate(mongoDbFactory);
		return template;
	}

	public static void main(String[] args)  {
		ConfigurableApplicationContext ctx = SpringApplication.run(CharityCrudApplication.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);

		}


	}

}
