package com.warframepda.www.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long orderid;

    private int price;

    @ManyToOne
    @JoinColumn(name = "partid", nullable = false)
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Part part;

    @ManyToOne
    @JoinColumn(name = "sellerid", nullable = false)
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Seller seller;

    public Order() {
    }

    public Order(int price, Part part, Seller seller) {
        this.price = price;
        this.part = part;
        this.seller = seller;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
