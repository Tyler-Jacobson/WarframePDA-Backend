package com.warframepda.www.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partdetails")
public class PartDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long partdetailid;

    private String partname;

    @OneToMany(mappedBy = "partdetail", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "partdetail", allowSetters = true)
    private List<Part> parts = new ArrayList<>();

    public PartDetail() {
    }

    public PartDetail(String partname) {
        this.partname = partname;
    }

    public long getPartdetailid() {
        return partdetailid;
    }

    public void setPartdetailid(long partdetailid) {
        this.partdetailid = partdetailid;
    }

    public String getPartname() {
        return partname;
    }

    public void setPartname(String partname) {
        this.partname = partname;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
