package com.square.shoppingcart.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Product entity
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

//    @Column(name = "start_time")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date startTime;
//
//    @Column(name = "end_time")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date endTime;

    @Column(name = "description")
    private String description;

    @Column(name = "creation_timestamp")
    @CreationTimestamp
    private Timestamp creationTimestamp;

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

    public Boolean getIsActive() { return isActive; }

    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

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
        Promotion promotion = (Promotion) o;
        return Objects.equals(id, promotion.id) && Objects.equals(isActive, promotion.isActive) && Objects.equals(description, promotion.description) && Objects.equals(creationTimestamp, promotion.creationTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isActive, description, creationTimestamp);
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", description='" + description + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                '}';
    }
}