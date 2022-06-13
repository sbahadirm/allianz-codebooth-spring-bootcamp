package com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.dao;

import com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
public interface CategoryDao extends JpaRepository<Category, Long> {
}
