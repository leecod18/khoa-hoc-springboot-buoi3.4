package com.example.demo.service;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.enity.Product;

public interface ProductService {
     ProductResponse create(ProductRequest productRequest);
     ProductResponse findById(Long id);
     void deleteById(Long id);
     ProductResponse updateById(Long id,ProductRequest productRequest);
     ProductResponse toResponse(Product saveProduct);
}
