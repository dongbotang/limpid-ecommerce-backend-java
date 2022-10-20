package com.square.shoppingcart.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Product entity for all products
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "price", nullable = false, precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;

    @Column(name = "quantity_reserved", nullable = false)
    private Integer quantityReserved;

    //quantity_sold_accumulated
    //quantity_wasted

    @Column(name = "description")
    private String description;

    @Column(name = "creation_timestamp")
    @CreationTimestamp
    private Timestamp creationTimestamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory productCategory;

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Timestamp getCreationTimestamp() { return creationTimestamp; }

    public void setCreationTimestamp(Timestamp creationTimestamp) { this.creationTimestamp = creationTimestamp; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Integer getQuantityReserved() {
        return quantityReserved;
    }

    public void setQuantityReserved(Integer quantityReserved) {
        this.quantityReserved = quantityReserved;
    }

    public Integer getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

//    public Integer getQuantitySoldAccumulated() {
//        return quantitySoldAccumulated;
//    }
//
//    public void setQuantitySoldAccumulated(Integer quantitySoldAccumulated) {
//        this.quantitySoldAccumulated = quantitySoldAccumulated;
//    }
//
//    public Integer getQuantityWasted() {
//        return quantityWasted;
//    }
//
//    public void setQuantityWasted(Integer quantityWasted) {
//        this.quantityWasted = quantityWasted;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(sku, product.sku) && Objects.equals(price, product.price) && Objects.equals(quantityInStock, product.quantityInStock) && Objects.equals(quantityReserved, product.quantityReserved) && Objects.equals(description, product.description) && Objects.equals(creationTimestamp, product.creationTimestamp) && Objects.equals(productCategory, product.productCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sku, price, quantityInStock, quantityReserved, description, creationTimestamp, productCategory);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", quantityInStock=" + quantityInStock +
                ", quantityReserved=" + quantityReserved +
                ", description='" + description + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", productCategory=" + productCategory +
                '}';
    }
}
