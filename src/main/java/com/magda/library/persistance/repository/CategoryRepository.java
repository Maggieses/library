package com.magda.library.persistance.repository;

import com.magda.library.persistance.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    List<CategoryEntity> findAll();

    CategoryEntity findCategoryByNameIgnoreCase(final String name);
}
