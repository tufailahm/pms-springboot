package com.revature.pms.controller;

import com.revature.pms.dao.ProductDAO;
import com.revature.pms.model.Product;
import com.revature.pms.services.ProductService;
import com.revature.pms.utilities.CheckNumber;
import com.revature.pms.utilities.GenerateRandomNumber;
import com.revature.pms.utilities.PasswordHashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {


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

    @PostMapping   //localhost:8084/product                   -HTTP method - POST
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        ResponseEntity responseEntity = null;

        if (productService.isProductExists(product.getProductId())) {
            responseEntity = new ResponseEntity<String>
                    ("Cannot save because product with product id :" + product.getProductId() + " already exists", HttpStatus.CONFLICT);   //409
        } else {
            result = productService.addProduct(product);
            if (result) {
                responseEntity = new ResponseEntity<String>
                        ("Successfully Saved your product:" + product.getProductId(), HttpStatus.OK);        //200
            } else {
                responseEntity = new ResponseEntity<String>
                        ("Cannot save because product because price or qoh is negative", HttpStatus.NOT_ACCEPTABLE);        //406
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
    public String getProducts() {
        return "All products";
    }

    @GetMapping("{pId}")     //localhost:8084/product/78
    public Product getProduct(@PathVariable("pId") int productId) {
        System.out.println("Fetching details about by product id  :" + productId);
        //call the methods to fetch product details of this product id
        Product product = new Product(productId, "Dummy", 99, 100);
        return product;
    }

    @GetMapping("/searchProductByName/{pName}")     //localhost:8084/product/searchProductByName/Laptop
    public Product getProductByName(@PathVariable("pName") String productName) {
        System.out.println("Fetching details about product name  :" + productName);
        //call the methods to fetch product details of this productname
        Product product = new Product(-1, productName, 99, 100);
        return product;
    }

    //localhost:8084/product/filterProductByPrice/100/and/500
    //Here is the result for product in the price range of 100 and 500

    @GetMapping("/filterProductByPrice/{lowerPrice}/and/{upperPrice}")
    public String filterProductByPrice(@PathVariable("lowerPrice") int lowerPrice, @PathVariable("upperPrice") int upperPrice) {
        //call the methods to fetch product details of this productname
        return "Here is the result tt for product in the price range of " + lowerPrice + " and " + upperPrice;
    }

    //localhost:8084/product/outOfStockProductDetails/350

    //Output : Order with qoh less than 50 should be reordered immediality
    //Output : Order with qoh less than 350 should be reordered immediality
    @GetMapping("/outOfStockProductDetails/{pMinStock}")
    public String getOutOfStockProductDetails(@PathVariable("pMinStock") int productMinStock) {
        return "Order with qoh less than " + productMinStock + " should be reordered immediately..";
    }


    /*
    Deleting a single product by id
     */
    @DeleteMapping("{pId}")     //localhost:8084/product/78                        -HTTP method - DELETE
    public String deleteProduct(@PathVariable("pId") int productId) {
        System.out.println("Deleting details  by product id  :" + productId);
        //call the methods to delete product details of this product id
        return "Deleting details  by product id  :" + productId;
    }

    /*
    This method will save a product in DB
     */


    /*
This method will update a product in DB
 */
    @PutMapping   //localhost:8084/product                   -HTTP method - PUT
    public String updateProduct(@RequestBody Product product) {
        System.out.println("Updating details  of :" + product);
        //call the methods to save  product
        return "Successfully updated product   :" + product;
    }
}
