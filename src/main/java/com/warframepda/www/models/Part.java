package com.warframepda.www.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long partid;


    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "part", allowSetters = true)
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "itemid",
            nullable = false)
    @JsonIgnoreProperties(value = "parts", allowSetters = true)
    private Item item;


    @ManyToOne
    @JoinColumn(name = "partdetailid",
            nullable = false)
    @JsonIgnoreProperties(value = "parts", allowSetters = true)
    private PartDetail partdetail;

    public Part() {
    }

    public Part(Item item, PartDetail partdetail) {
        this.item = item;
        this.partdetail = partdetail;
    }

    public long getPartid() {
        return partid;
    }

    public void setPartid(long partid) {
        this.partid = partid;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
