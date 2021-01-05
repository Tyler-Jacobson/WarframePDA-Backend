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

    @ManyToMany()
    @JoinTable(name = "orderssellers",
            joinColumns = @JoinColumn(name = "orderid"),
            inverseJoinColumns = @JoinColumn(name = "sellerid"))
    @JsonIgnoreProperties(value = "orders", allowSetters = true)
    Set<Seller> sellers = new HashSet<>();


    public Order() {
    }

    public Order(int price, Part part) {
        this.price = price;
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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Set<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(Set<Seller> sellers) {
        this.sellers = sellers;
    }
}
