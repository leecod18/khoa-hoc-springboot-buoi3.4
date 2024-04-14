package com.example.demo.config;

import com.example.demo.enity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StarupConfig  {
    @Bean
    public CommandLineRunner starup(CategoryRepository repository){
        return args -> {
//            Category category = new Category();
//            category.setName("Tai nghe");
//            repository.save(category);
//
//            Category category2 = new Category();
//            category2.setName("Loa");
//            repository.save(category2);

        };

    }

}
