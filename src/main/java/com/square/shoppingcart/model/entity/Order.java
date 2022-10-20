package com.square.shoppingcart.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Order entity for all orders
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // order_no
    // invoice_id
    // customer_id

    /**
     * Order status includes but not limited to -
     * draft (shopping cart), paid, dispatched, delivered, completed, return_lodged, return_shipped,
     * returned_received, refund_issued
     */
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "is_gift")
    private Boolean isGift = false;

    @Column(name = "note")
    private String note;

    @Column(name = "creation_timestamp")
    @CreationTimestamp
    private Timestamp creationTimestamp;

    public Timestamp getCreationTimestamp() { return creationTimestamp; }

    public void setCreationTimestamp(Timestamp creationTimestamp) { this.creationTimestamp = creationTimestamp; }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getIsGift() { return isGift; }

    public void setIsGift(Boolean isGift) { this.isGift = isGift; }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(status, order.status) && Objects.equals(isGift, order.isGift) && Objects.equals(note, order.note) && Objects.equals(creationTimestamp, order.creationTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, isGift, note, creationTimestamp);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", isGift=" + isGift +
                ", note='" + note + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}
