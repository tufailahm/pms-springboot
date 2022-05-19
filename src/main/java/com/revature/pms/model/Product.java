package com.revature.pms.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Product {

    private int productId;
    private String productName;
    private int qoh;
    private int price;

    public String displayMessage(){
       return "This message is coming from product class";
    }
}
