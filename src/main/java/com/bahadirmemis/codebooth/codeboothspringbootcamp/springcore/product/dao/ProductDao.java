package com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.dao;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface ProductDao extends JpaRepository<Product, Long> {
}
