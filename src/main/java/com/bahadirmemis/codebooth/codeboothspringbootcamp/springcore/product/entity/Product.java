package com.bahadirmemis.codebooth.codeboothspringbootcamp.springcore.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Bahadır Memiş
 * @since 1.0.0
 */
@Entity
@Getter
@Setter
@Table(name = "PRODUCT", indexes = {
        @Index(name = "IX_PRODUCT_ID_CATEGORY", columnList = "ID_CATEGORY"),
        @Index(name = "IX_PRODUCT_NAME_PRICE", columnList = "NAME, PRODUCT_PRICE")
})
public class Product {

    @Id
    @SequenceGenerator(name = "Product", sequenceName = "PRODUCT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "Product")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORY", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY_ID"))
    private Category category;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "PRODUCT_PRICE", precision = 19, scale = 2)
    private BigDecimal price;

    @Transient
    private Boolean isActive;

    @Temporal(TemporalType.DATE)
    private Date expireDate;

}
