package com.revature.pms.services;

import com.revature.pms.dao.ProductDAO;
import com.revature.pms.model.Product;
import com.revature.pms.utilities.CheckNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Autowired()
    CheckNumber checkNumber;

    @Override
    public boolean addProduct(Product product) {
        System.out.println("Adding product in service");
        if (checkNumber.checkNegativeInt(product.getQoh(), product.getPrice())) {
            productDAO.save(product);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public boolean deleteProduct(int productId) {
        productDAO.deleteById(productId);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        productDAO.save(product);
        return false;
    }

    @Override
    public Product getProduct(int productId) {
        Product pr = productDAO.getById(productId);
        return pr;
    }

    @Override
    public boolean isProductExists(int productId) {
        return productDAO.existsById(productId);
    }


    @Override
    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    //by default these are not exposed

    @Override
    public List<Product> getProduct(String productName) {
        return null;
    }

    @Override
    public List<Product> filterProductByPrice(int minimumPrice, int maximumPrice) {
        return null;
    }

    @Override
    public List<Product> getProductByPrice(int Price) {
        return null;
    }

    @Override
    public List<Product> getProductByQoh(int qoh) {
        return null;
    }

    @Override
    public List<Product> getProductByGreaterQoh(int greaterQoh) {
        return null;
    }
}
