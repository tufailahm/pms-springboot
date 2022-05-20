package com.revature.pms.config;

import com.revature.pms.model.Product;
import com.revature.pms.utilities.PasswordHashing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

//   @Scope(value = "prototype")
    @Bean
    public Product product() {
        return new Product();
    }
/*
    @Bean
    public PasswordHashing getPasswordHashing(){
        return new PasswordHashing();
    }*/
}
