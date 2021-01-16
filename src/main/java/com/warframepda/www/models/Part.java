package com.warframepda.www.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long partid;

    @Column(nullable = false)
    private String partname;


    @ManyToOne
    @JoinColumn(name = "itemid",
            nullable = false)
    @JsonIgnoreProperties(value = "parts", allowSetters = true)
    private Item item;

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "part", allowSetters = true)
    private List<Order> orders = new ArrayList<>();


    public Part() {
    }

    public Part(String partname, Item item) {
        this.partname = partname;
        this.item = item;
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

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }
}
