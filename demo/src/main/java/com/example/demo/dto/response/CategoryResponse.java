package com.example.demo.dto.response;

import com.example.demo.enity.Product;

import java.util.List;

public class CategoryResponse {
    private Long id;

    private String name;


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
}
