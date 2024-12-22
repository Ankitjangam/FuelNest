package com.FuelNest.Application.Controller;

import com.FuelNest.Application.Model.Products;
import com.FuelNest.Application.Service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addProducts(@RequestBody Products products) {

         Map<String,Object> response = new HashMap<>();
         productServices.addProducts(products);
         response.put("message","Product Added Successfully.....");
         response.put("Success",true);
         return ResponseEntity.ok(response);

    }

////////////Get All Products

    @GetMapping("/{Id}")
    public ResponseEntity<Map<String, Object>> getProduct(@PathVariable("Id") Long id) {
        Map<String, Object> response = new HashMap<>();
        Products product = productServices.getProductById(id);

        if (product != null) {
            response.put("message", product);
            response.put("product", product);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Product not found.");
            return ResponseEntity.status(404).body(response);
        }
    }



    @PutMapping("/{Id}")
    public ResponseEntity<Map<String, Object>> UpdateProduct(@PathVariable("Id") Long id) {
        Map<String, Object> response = new HashMap<>();
        Products product = productServices.getProductById(id);

        if (product != null) {
            response.put("success", true);
            response.put("product", "Product Updated Successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Product not found.");
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        Map<String, Object> response = new HashMap<>();
        List<Products> productList = productServices.getAllProducts();

        if (productList != null && !productList.isEmpty()) {
            response.put("success", true);
            response.put("products", productList);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "No products found.");
            return ResponseEntity.status(404).body(response);
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();

       String isDeleted = productServices.deleteProduct(id);

        if (!isDeleted.isEmpty()) {
            response.put("success", true);
            response.put("message", "Product deleted successfully.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Product not found.");
            return ResponseEntity.status(404).body(response);
        }
    }
    }

