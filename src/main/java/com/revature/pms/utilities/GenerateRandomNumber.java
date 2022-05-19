package com.revature.pms.utilities;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class GenerateRandomNumber {

    public double getRandomNumber(){
        return  Math.random();
    }
}
