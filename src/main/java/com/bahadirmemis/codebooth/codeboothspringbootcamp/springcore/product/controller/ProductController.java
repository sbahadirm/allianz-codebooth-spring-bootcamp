package com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.controller;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.dao.ProductDao;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductDao productDao;

    @PostMapping
    public Product save(@RequestBody Product product){
        return productDao.save(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product){

        if (product == null || product.getId() == null){
            throw new RuntimeException("Product and product id cannot be null!");
        }

        boolean isExist = productDao.existsById(product.getId());
        if (!isExist){
            throw new RuntimeException("Product does not exist!");
        }

        return productDao.save(product);
    }

    @GetMapping
    public List<Product> getAll(){
        return productDao.findAll();
    }

    @GetMapping("/{id}")
    public Product get(@PathVariable Long id){
        return productDao.findById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        productDao.deleteById(id);
    }
}
