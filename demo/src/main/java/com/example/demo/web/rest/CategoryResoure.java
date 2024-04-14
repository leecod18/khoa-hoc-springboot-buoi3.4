package com.example.demo.web.rest;

import com.example.demo.dto.request.CategoryRequest;
import com.example.demo.dto.response.CategoryResponse;
import com.example.demo.dto.response.ProductResponse;
import com.example.demo.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/categorys"))
public class CategoryResoure {
    private final CategoryService categoryService;
    public CategoryResoure(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping
    public CategoryResponse create(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.createCategory(categoryRequest);
    }
    @GetMapping("/{id}")
    public CategoryResponse find(@PathVariable Long id){
        return categoryService.findById(id);
    }
    @GetMapping("/{id}/products")
    public List<ProductResponse> findProductsByCategoryID(@PathVariable Long id){
        return categoryService.findProductsByCategoryID(id);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
    }
    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        return categoryService.updateCategory(id, categoryRequest);
    };

}
