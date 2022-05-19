package com.revature.pms.utilities;

import com.revature.pms.model.Product;
import org.springframework.stereotype.Component;

@Component
public class CheckNumber {
    public boolean checkNegativeInt(int qoh,int price) {
        if (qoh < 0 || price< 0) {
            return false;
        } else {
            return true;
        }
    }
}

