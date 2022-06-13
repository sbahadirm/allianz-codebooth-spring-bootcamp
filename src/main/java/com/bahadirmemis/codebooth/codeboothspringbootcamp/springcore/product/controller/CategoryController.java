package com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.controller;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.dao.CategoryDao;
import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryDao categoryDao;

    @PostMapping
    public Category save(@RequestBody Category category){
        return categoryDao.save(category);
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryDao.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryDao.deleteById(id);
    }
}
