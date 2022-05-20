package com.revature.pms.services;

import com.revature.pms.dao.ProductDAO;
import com.revature.pms.model.Product;
import com.revature.pms.utilities.CheckNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductDAO productDAO;

    @Autowired()
    CheckNumber checkNumber;

    @Override
    public boolean addProduct(Product product) {
        LOGGER.info("Adding product in service");
        if (checkNumber.checkNegativeInt(product.getQoh(), product.getPrice())) {
            productDAO.save(product);
            return true;

        } else {
            LOGGER.warn("Failed to save product since price or qoh is negative");
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
        if (checkNumber.checkNegativeInt(product.getQoh(), product.getPrice())) {
            productDAO.save(product);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Product getProduct(int productId) {
        Product pr = productDAO.getById(productId);
        return pr;
    }

    @Override
    public Boolean isProductExists(Integer productId) {
        return productDAO.existsById(productId);
    }


    @Override
    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    //by default these are not exposed

    @Override
    public List<Product> getProduct(String productName) {
        return productDAO.getProductByName(productName);
    }

    @Override
    public List<Product> filterProductByPrice(int minimumPrice, int maximumPrice) {
        return productDAO.findByPriceBetween(minimumPrice,maximumPrice);
    }

    @Override
    public List<Product> getProductByPrice(int price) {
        return productDAO.findByPrice(price);
    }

    @Override
    public List<Product> getProductByQoh(int qoh) {
        return productDAO.findByQoh(qoh);
    }

    @Override
    public List<Product> getProductByGreaterQoh(int greaterQoh) {
        return null;
    }

    @Override
    public List<Product> getProductByLessQoh(int qoh) {
        return getProductByLessQoh(qoh);
    }
}
