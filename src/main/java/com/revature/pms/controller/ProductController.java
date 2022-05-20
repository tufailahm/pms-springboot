package com.revature.pms.controller;

import com.revature.pms.dao.ProductDAO;
import com.revature.pms.model.Product;
import com.revature.pms.services.ProductService;
import com.revature.pms.utilities.CheckNumber;
import com.revature.pms.utilities.GenerateRandomNumber;
import com.revature.pms.utilities.PasswordHashing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired()
    Product product;
    @Autowired(required = false)        //If the bean is available , then inject it,
    //If the bean is not available , then dont give me an error
    PasswordHashing passwordHashing;
    @Autowired
    GenerateRandomNumber randomNumber;


    @Autowired
    ProductService productService;
    boolean result;

    public ProductController(){
        System.out.println("Product controller called");
    }

    @PostConstruct              //lifecycle method
    public void callMeFirst(){
        //for initilize
        result = true;
        System.out.println("Call me First called");
    }

    @PreDestroy
    public void callmeLast(){

    }
    @PostMapping   //localhost:8084/product                   -HTTP method - POST
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        ResponseEntity responseEntity = null;
        LOGGER.trace("TRACE - Save product started the execution");
        LOGGER.debug("DEBUG - Save product started the execution");
        LOGGER.info("INFO - Save product started the execution");
        LOGGER.warn("WARN - Save product started the execution");
        LOGGER.error("ERROR - Save product started the execution");

        if (productService.isProductExists(product.getProductId())) {
            LOGGER.warn("Product with product id :"+product.getProductId()+ "already exists");
            responseEntity = new ResponseEntity<String>
                    ("Cannot save because product with product id :" + product.getProductId() + " already exists", HttpStatus.CONFLICT);   //409
        } else {
            result = productService.addProduct(product);
            if (result) {
                responseEntity = new ResponseEntity<String>
                        ("Successfully Saved your product:" + product.getProductId(), HttpStatus.OK);        //200
                LOGGER.info("Product with product id :"+product.getProductId()+ " saved successfully");

            } else {
                responseEntity = new ResponseEntity<String>
                        ("Cannot save because product because price or qoh is negative", HttpStatus.NOT_ACCEPTABLE);        //406
                LOGGER.error("Product with product id :"+product.getProductId()+ "cannot be saved because of negative qoh or price");

            }
        }
        return responseEntity;
    }


    @GetMapping("/home")     //localhost:8084/product/home
    public String home() {
        double result = randomNumber.getRandomNumber();
        return "##Welcome to Revature Product App## "
                + product.displayMessage() + " and password hashing message is : "
                + passwordHashing.getHashedPassword()
                + " Random number for this request is :" + result;
    }

    @GetMapping     //localhost:8084/product
    public ResponseEntity<List<Product>> getProducts() {
        ResponseEntity responseEntity = null;
        List<Product> products = new ArrayList<Product>();
        products = productService.getProducts();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("{pId}")     //localhost:8084/product/78
    public ResponseEntity<Product> getProduct(@PathVariable("pId") int productId) {
        System.out.println("Fetching details about by product id  :" + productId);
        //call the methods to fetch product details of this product id
        ResponseEntity responseEntity = null;
        Product product1 = new Product();
        if (productService.isProductExists(productId)) {
            product1 = productService.getProduct(productId);
            responseEntity = new ResponseEntity<Product>(product1, HttpStatus.OK);   //409
        } else {
            responseEntity = new ResponseEntity<Product>
                    (product1, HttpStatus.NO_CONTENT);        //204
        }
        return responseEntity;
    }


    /*
    Deleting a single product by id
     */
    @DeleteMapping("{pId}")     //localhost:8084/product/78                        -HTTP method - DELETE
    public ResponseEntity<String> deleteProduct(@PathVariable("pId") int productId) {
        System.out.println("Deleting details  by product id  :" + productId);
        ResponseEntity<String> responseEntity;

        if (!(productService.isProductExists(productId))) responseEntity = new ResponseEntity<String>
                ("Cannot delete as id doesn't exist", HttpStatus.NO_CONTENT);
        else {
            if (productService.deleteProduct(productId)) responseEntity = new ResponseEntity<String>
                    (productId + " deleted", HttpStatus.OK);
            else responseEntity = new ResponseEntity<String>
                    (" Cannot delete, something went wrong", HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }


    @PutMapping   //localhost:8084/product                   -HTTP method - PUT
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        ResponseEntity responseEntity = null;
        System.out.println("Updating details  of :" + product);
        if (productService.isProductExists(product.getProductId())) {
            boolean result = productService.updateProduct(product);
            if (result) {
                responseEntity = new ResponseEntity<String>
                        ("Successfully updated your product:" + product.getProductId(), HttpStatus.OK); //send 200 with response
            } else {
                responseEntity = new ResponseEntity<String>
                        ("not  updated your product because price or qoh cannot be negative" + product.getProductId(), HttpStatus.NOT_MODIFIED); //send 200 with resp
            }
        } else {
            responseEntity = new ResponseEntity<String>
                    ("Cannot update product at ID: " + product.getProductId() + ", check that it exists ", HttpStatus.NOT_ACCEPTABLE);
        }
        return responseEntity;
    }


    ///below methods


    @GetMapping("/searchProductByName/{pName}")     //localhost:8084/product/searchProductByName/Laptop
    public List<Product> getProductByName(@PathVariable("pName") String productName) {
        System.out.println("Fetching details about product name  :" + productName);
        //call the methods to fetch product details of this productname
        return productService.getProduct(productName);

    }

            //localhost:8084/product/filterProductByPrice/100/and/500
    //Here is the result for product in the price range of 100 and 500

    @GetMapping("/filterProductByPrice/{lowerPrice}/and/{upperPrice}")
    public List<Product> filterProductByPrice(@PathVariable("lowerPrice") int lowerPrice, @PathVariable("upperPrice") int upperPrice) {
        //call the methods to fetch product details of this productname
        List<Product> products = productService.filterProductByPrice(lowerPrice,upperPrice);
        return products;
    }

    //localhost:8084/product/outOfStockProductDetails/350

    //Output : Order with qoh less than 50 should be reordered immediality
    //Output : Order with qoh less than 350 should be reordered immediality
    @GetMapping("/outOfStockProductDetails/{pMinStock}")
    public String getOutOfStockProductDetails(@PathVariable("pMinStock") int productMinStock) {
        return "Order with qoh less than " + productMinStock + " should be reordered immediately..";
    }

}
