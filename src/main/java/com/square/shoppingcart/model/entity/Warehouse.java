package com.square.shoppingcart.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Product entity
 * @author Sandra Dongbo Tang
 * @since 2022-10-16
 */

@Entity
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "post_code", nullable = false)
    private String postCode;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    public String getCountryCode() {
        return countryCode;
    }

    @Column(name = "contact")
    private String contact;

    @Column(name = "phone")
    private String phone;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(id, warehouse.id) && Objects.equals(name, warehouse.name) && Objects.equals(address, warehouse.address) && Objects.equals(postCode, warehouse.postCode) && Objects.equals(countryCode, warehouse.countryCode) && Objects.equals(contact, warehouse.contact) && Objects.equals(phone, warehouse.phone) && Objects.equals(fax, warehouse.fax) && Objects.equals(email, warehouse.email) && Objects.equals(description, warehouse.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, postCode, countryCode, contact, phone, fax, email, description);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", contact='" + contact + '\'' +
                ", phone='" + phone + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
