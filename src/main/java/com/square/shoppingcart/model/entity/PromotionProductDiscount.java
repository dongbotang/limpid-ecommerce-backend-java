package com.square.shoppingcart.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Promotion Product Discount entity
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Entity
@Table(name = "promotion_product_discount")
public class PromotionProductDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "trigger_quantity", nullable = false)
    private Integer triggerQuantity = 1;

    @Column(name = "discount_quantity")
    private Integer discountQuantity = -1; // -1=discount apply to all ordered quantity

    @Column(name = "percentage_off")
    private BigDecimal percentageOff = BigDecimal.valueOf(100.00); // 100.00=free

    @Column(name = "description")
    private String description;

    @Column(name = "creation_timestamp")
    @CreationTimestamp
    private Timestamp creationTimestamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trigger_product_id", nullable = false)
    private Product triggerProduct;

    @ManyToOne(optional = false)
    @JoinColumn(name = "discount_product_id", nullable = false)
    private Product discountProduct;

    public Product getDiscountProduct() {
        return discountProduct;
    }

    public void setDiscountProduct(Product discountProduct) {
        this.discountProduct = discountProduct;
    }

    public Product getTriggerProduct() {
        return triggerProduct;
    }

    public void setTriggerProduct(Product product) {
        this.triggerProduct = product;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Timestamp getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Timestamp creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPercentageOff() {
        return percentageOff;
    }

    public void setPercentageOff(BigDecimal percentageOff) {
        this.percentageOff = percentageOff;
    }

    public Integer getDiscountQuantity() {
        return discountQuantity;
    }

    public void setDiscountQuantity(Integer discountQuantity) {
        this.discountQuantity = discountQuantity;
    }

    public Integer getTriggerQuantity() {
        return triggerQuantity;
    }

    public void setTriggerQuantity(Integer triggerQuantity) {
        this.triggerQuantity = triggerQuantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionProductDiscount that = (PromotionProductDiscount) o;
        return Objects.equals(id, that.id) && Objects.equals(triggerQuantity, that.triggerQuantity) && Objects.equals(discountQuantity, that.discountQuantity) && Objects.equals(percentageOff, that.percentageOff) && Objects.equals(description, that.description) && Objects.equals(creationTimestamp, that.creationTimestamp) && Objects.equals(promotion, that.promotion) && Objects.equals(triggerProduct, that.triggerProduct) && Objects.equals(discountProduct, that.discountProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, triggerQuantity, discountQuantity, percentageOff, description, creationTimestamp, promotion, triggerProduct, discountProduct);
    }
}