package com.warframepda.www.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderid;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String seller;

    @ManyToOne
    @JoinColumn(name = "partid", nullable = false)
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    private Part part;



    public Order() {
    }

    public Order(int price, String seller, Part part) {
        this.price = price;
        this.seller = seller;
        this.part = part;
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

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

}
