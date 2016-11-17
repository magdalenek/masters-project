package com.landing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@Controller
@EnableAutoConfiguration
public class LandingPageApplication {


	@RequestMapping("/")
	public String servePage() {
		return "main.html";
	}

	public static void main(String[] args) {
		SpringApplication.run(LandingPageApplication.class, args);
	}
}



