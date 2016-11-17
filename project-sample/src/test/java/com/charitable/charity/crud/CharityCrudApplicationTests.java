package com.charitable.charity.crud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CharityCrudApplication.class)
public class CharityCrudApplicationTests {

	@Test
	public void contextLoads() {
		try {
			System.out.println("is running");
		}catch (Exception e) {
			System.out.print("Is not running");
		}
	}

}
