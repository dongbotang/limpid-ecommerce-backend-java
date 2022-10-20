package com.square.shoppingcart.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Promotion Order Discount entity
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Entity
@Table(name = "promotion_order_discount")
public class PromotionOrderDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "trigger_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal triggerAmount;

    @Column(name = "percentage_remain", nullable = false)
    private float percentageRemain;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_timestamp")
    @CreationTimestamp
    private Timestamp creationTimestamp;

    @ManyToOne(optional = false)
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

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

    public float getPercentageRemain() {
        return percentageRemain;
    }

    public void setPercentageRemain(float percentageRemain) {
        this.percentageRemain = percentageRemain;
    }

    public BigDecimal getTriggerAmount() {
        return triggerAmount;
    }

    public void setTriggerAmount(BigDecimal triggerAmount) {
        this.triggerAmount = triggerAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromotionOrderDiscount that = (PromotionOrderDiscount) o;
        return Float.compare(that.percentageRemain, percentageRemain) == 0 && Objects.equals(id, that.id) && Objects.equals(promotion, that.promotion) && Objects.equals(triggerAmount, that.triggerAmount) && Objects.equals(description, that.description) && Objects.equals(creationTimestamp, that.creationTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, promotion, triggerAmount, percentageRemain, description, creationTimestamp);
    }

    @Override
    public String toString() {
        return "PromotionOrderDiscount{" +
                "id=" + id +
                ", promotion=" + promotion +
                ", triggerAmount=" + triggerAmount +
                ", percentageRemain=" + percentageRemain +
                ", description='" + description + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}