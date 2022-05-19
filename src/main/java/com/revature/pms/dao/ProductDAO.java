package com.revature.pms.dao;

import com.revature.pms.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductDAO extends JpaRepository<Product,Integer> {
}
