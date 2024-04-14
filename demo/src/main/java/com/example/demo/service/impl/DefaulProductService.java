package com.example.demo.service.impl;

import com.example.demo.dto.request.ProductRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.enity.Category;
import com.example.demo.enity.Product;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class DefaulProductService implements ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    public DefaulProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public ProductResponse create(ProductRequest request) {
        //kiem tra xem id có ton tai truoc khi them khong
        Category categoryRef= categoryRepository.getReferenceById(request.getCategoryId());
        try {
            categoryRef.getId();
        }catch(EntityNotFoundException e){
            System.err.println(e.getMessage());
            throw new BadRequestException(e.getMessage(),e);
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setDes(request.getDes());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());
        product.setCategoryId(categoryRef);
        //save xuong database
        Product saveProduct = repository.save(product);

        ProductResponse productResponse = toResponse(saveProduct);
        return productResponse;
    }

    public ProductResponse toResponse(Product saveProduct) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(saveProduct.getId());
        productResponse.setName(saveProduct.getName());
        productResponse.setDes(saveProduct.getDes());
        productResponse.setQuantity(saveProduct.getQuantity());
        productResponse.setPrice(saveProduct.getPrice());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(saveProduct.getCategory().getId());
        categoryResponse.setName(saveProduct.getCategory().getName());

        productResponse.setCategory(categoryResponse);
        return productResponse;
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Product with id " + id + " not found"));

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDes(product.getDes());
        productResponse.setQuantity(product.getQuantity());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

    @Override
    public void deleteById(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Product with id " + id + " not found"));
        repository.delete(product);
    }
    @Override
    public ProductResponse updateById(Long id,ProductRequest request) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Product with id " + id + " not found"));

        product.setName(request.getName());
        product.setDes(request.getDes());
        product.setQuantity(request.getQuantity());
        product.setPrice(request.getPrice());

        Product saveProduct = repository.save(product);

        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(saveProduct.getId());
        productResponse.setName(saveProduct.getName());
        productResponse.setDes(saveProduct.getDes());
        productResponse.setQuantity(saveProduct.getQuantity());
        productResponse.setPrice(saveProduct.getPrice());


        return productResponse;
    }

}
