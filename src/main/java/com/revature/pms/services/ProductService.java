package com.revature.pms.services;

import com.revature.pms.model.Product;

import java.util.List;

public interface ProductService {
    public boolean addProduct(Product product);
    public boolean deleteProduct(int productId);
    public boolean updateProduct(Product product);
    public Product getProduct(int productId);
    public boolean isProductExists(int productId);
    public List<Product> getProduct(String productName);
    public List<Product> getProducts();

    public List<Product> filterProductByPrice(int minimumPrice,int maximumPrice);
    public List<Product> getProductByPrice(int Price);
    public List<Product> getProductByQoh(int qoh);
    public List<Product> getProductByGreaterQoh(int greaterQoh);


}
