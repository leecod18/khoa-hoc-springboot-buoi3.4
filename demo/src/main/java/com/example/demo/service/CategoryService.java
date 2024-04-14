package com.example.demo.service;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.enity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse findById(Long id);
    List<ProductResponse> findProductsByCategoryID(Long id);
    CategoryResponse updateCategory(Long id,CategoryRequest categoryRequest);
    void deleteCategoryById(Long id);
}
