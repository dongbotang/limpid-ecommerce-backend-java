package com.square.shoppingcart.model.dto;

import java.util.Objects;

/**
 * This is a data model used by RestController to add or remove a product into/from shopping card
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */
public class ShoppingCartItem {
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String note;

    public ShoppingCartItem(Long orderId, Long productId, Integer quantity, String note) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.note = note;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return Objects.equals(orderId, that.orderId)
                && Objects.equals(productId, that.productId)
                && Objects.equals(quantity, that.quantity)
                && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, quantity, note);
    }

    @Override
    public String toString() {
        return "ShoppingCartProduct{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", note=" + note +
                "}";
    }
}
