package com.revature.pms;

import com.revature.pms.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PmsSpringBootApplication {

	@Autowired
	Product product1;

	@Autowired
	Product product2;

	public static void main(String[] args) {
		SpringApplication.run(PmsSpringBootApplication.class, args);
	}

}
