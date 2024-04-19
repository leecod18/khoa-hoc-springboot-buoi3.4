package com.example.demo.service.impl;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.enity.Category;
import com.example.demo.enity.Product;
import com.example.demo.exception.ResourceNotFound;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.ArrayList;
import java.util.List;


@Service
public class DefaulCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductService productService;


    public DefaulCategoryService(CategoryRepository categoryRepository, ProductService productService) {
     this.categoryRepository = categoryRepository;
        this.productService = productService;

    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        //save to database
        Category saveCategory = categoryRepository.save(category);
        //chuyen sang response
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(saveCategory.getId());
        categoryResponse.setName(saveCategory.getName());
        return categoryResponse;
    }

    @Override
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id+"Id not found"));
        //tim thay sau set lai vao reponse
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        return categoryResponse;
    }
    @Override
    public List<ProductResponse> findProductsByCategoryID(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id+"Id not found"));

        List<ProductResponse> productRes= new ArrayList<>();
        List<Product> products = category.getProducts();
        for(Product product: products){
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setDes(product.getDes());
            productResponse.setQuantity(product.getQuantity());
            productResponse.setPrice(product.getPrice());

            //set data category in product
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(product.getCategory().getId());
            categoryResponse.setName(product.getCategory().getName());

            productResponse.setCategory(categoryResponse);
            productRes.add(productResponse);
        }

// cach 2
//        List<Product> products = category.getProducts();
//        List<ProductResponse> res = products.stream().map(p -> {
//            return productService.toResponse(p);
//        }).toList();
//    return res;

        return productRes;
    }
    @Override
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id+"Id not found"));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest){
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id+"Id not found"));
        category.setName(categoryRequest.getName());
        Category saveCategory = categoryRepository.save(category);

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(saveCategory.getId());
        categoryResponse.setName(saveCategory.getName());

        return categoryResponse;
    }
}
