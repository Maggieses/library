package com.magda.library.persistance.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.magda.library.model.Book;
import com.magda.library.persistance.entity.AuthorEntity;
import com.magda.library.persistance.entity.BookEntity;
import com.magda.library.persistance.entity.CategoryEntity;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BooksConverter {

    public static Book convertEntityToModel(final BookEntity bookEntity) {
        final Book book = new Book();
        BeanUtils.copyProperties(bookEntity, book);
        book.setDescription(bookEntity.getDescription().getDescription());

        final Set<String> authors = new HashSet<>();
        for (final AuthorEntity author : bookEntity.getAuthorEntities()) {
            String authorStr = String.format("%s %s (%s)", author.getFirstname(), author.getLastname(), author.getBirthDay().getYear());
            authors.add(authorStr);
        }
        book.setAuthors(authors);

        final Set<String> categories = new HashSet<>();
        for (final CategoryEntity category : bookEntity.getCategories()) {
            String categoriesStr = category.getName();
            categories.add(categoriesStr);
        }
        book.setCategories(categories);

        return book;
    }

    public static List<Book> convertEntityToModel(final List<BookEntity> bookEntities) {
        final List<Book> books = new ArrayList<>();
        for (final BookEntity bookEntity : bookEntities) {
            books.add(convertEntityToModel(bookEntity));
        }
        return books;
    }

}
