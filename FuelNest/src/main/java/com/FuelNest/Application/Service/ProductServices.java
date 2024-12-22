package com.FuelNest.Application.Service;

import com.FuelNest.Application.Model.Products;
import com.FuelNest.Application.Repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServices {

    @Autowired
    private ProductsRepository productsRepository;


    public String addProducts(Products products) {
        productsRepository.save(products);
      return"Product Added Successfully.........!";

    }

    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }


    public Products getProductById(Long id) {
        return productsRepository.findById(id).orElse(null);
    }




    public  String updateProduct(Long id,Products products) {
        Optional<Products> optional=productsRepository.findById(id);


        if(optional.isPresent()) {
            Products getProduct = optional.get();

            getProduct.setName(products.getName());
            getProduct.setDescription(products.getDescription());
            getProduct.setCategory(products.getCategory());
            getProduct.setPrice(products.getPrice());
            getProduct.setImageUrl(products.getImageUrl());

        }

           return  "Product Updated Successfully....!";

    }


    public String deleteProduct(Long Id) {

          productsRepository.deleteAllById(Collections.singleton(Id));
        return "Product Deleted Done!....";
    }

}
