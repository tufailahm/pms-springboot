package com.revature.pms.model;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "product1",schema = "projectone")
public class Product implements Serializable {
    @Id
    private int productId;
    private String productName;
    private int qoh;
    private int price;

    public String displayMessage(){
       return "This message is coming from product class";
    }
}
