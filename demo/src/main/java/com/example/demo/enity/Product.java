package com.example.demo.enity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false) // khong tuy y = bat buoc
    private String name;

    @Column(name = "descripton")
    private String des;
//    @Column(nullable = false,name = "quantity_of_product")
    @Basic(optional = false)
    private Integer quantity;

    @Basic(optional = false)
    private Double price;

    @ManyToOne
    private Category category;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategoryId(Category category) {
        this.category= category;
    }
}
