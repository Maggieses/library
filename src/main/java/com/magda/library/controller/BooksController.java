package com.magda.library.controller;

import com.magda.library.model.Book;
import com.magda.library.persistance.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable("bookId") final Long bookId) {
        return ResponseEntity.ok(bookService.getBook(bookId));
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody final Book bookEntity) {
        return ResponseEntity.ok(bookService.addBook(bookEntity));
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@PathVariable("bookId") final Long bookId) {
        bookService.deleteBook(bookId);
        return (ResponseEntity) ResponseEntity.noContent().build();
    }
}
