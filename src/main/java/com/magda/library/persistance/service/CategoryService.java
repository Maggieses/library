package com.magda.library.persistance.service;

import com.magda.library.persistance.entity.CategoryEntity;
import com.magda.library.persistance.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryEntity> getCategories() {
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategory(final String name) {
        final CategoryEntity categoryEntity = categoryRepository.findCategoryByNameIgnoreCase(name);
        return categoryEntity;
    }

    public CategoryEntity addCategory(final CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

}
