package com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
public class Category {

    @Id
    @SequenceGenerator(name = "Category", sequenceName = "CATEGORY_ID_SEQ")
    @GeneratedValue(generator = "Category")
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

}
