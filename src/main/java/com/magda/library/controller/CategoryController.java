package com.magda.library.controller;

import com.magda.library.model.Book;
import com.magda.library.persistance.entity.CategoryEntity;
import com.magda.library.persistance.service.BookService;
import com.magda.library.persistance.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getCategories() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{categoryName}")
    public ResponseEntity<CategoryEntity> getBook(@PathParam("categoryName") final String categoryName) {
        return ResponseEntity.ok(categoryService.getCategory(categoryName));
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> addBook(@RequestBody final CategoryEntity categoryEntity) {
        return ResponseEntity.ok(categoryService.addCategory(categoryEntity));
    }
}
