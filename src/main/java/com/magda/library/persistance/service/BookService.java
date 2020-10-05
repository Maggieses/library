package com.magda.library.persistance.service;

import com.magda.library.exception.BookNotFoundException;
import com.magda.library.model.Book;
import com.magda.library.persistance.entity.AuthorEntity;
import com.magda.library.persistance.entity.BookDescription;
import com.magda.library.persistance.entity.BookEntity;
import com.magda.library.persistance.entity.CategoryEntity;
import com.magda.library.persistance.repository.AuthorRepository;
import com.magda.library.persistance.repository.BookDescriptionRepository;
import com.magda.library.persistance.repository.BooksRepository;
import com.magda.library.persistance.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class BookService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookDescriptionRepository descriptionRepository;

    public List<Book> getBooks() {
        final List<BookEntity> bookEntities = booksRepository.findAll();
        return BooksConverter.convertEntityToModel(bookEntities);
    }

    public Book getBook(final Long bookId) {
        final String errorMessage = String.format("BookEntity with id='%s' not found.", bookId);
        final BookEntity bookEntity = booksRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(errorMessage));
        return BooksConverter.convertEntityToModel(bookEntity);
    }

    public Book addBook(final Book book) {
        final BookEntity bookEntity = new BookEntity();
        BeanUtils.copyProperties(book, bookEntity);
        bookEntity.setCategories(getCategories(book.getCategories()));
        bookEntity.setAuthorEntities(getAuthors(book.getAuthors()));
        bookEntity.setDescription(getDescription(book.getDescription()));

        final BookEntity addedBookEntity = booksRepository.save(bookEntity);
        return BooksConverter.convertEntityToModel(addedBookEntity);
    }

    private BookDescription getDescription(String description) {
        final BookDescription bookDescription = new BookDescription();
        bookDescription.setDescription(description);
        return descriptionRepository.save(bookDescription);
    }

    private Set<AuthorEntity> getAuthors(Set<String> authors) {
        final Set<AuthorEntity> authorEntities = new HashSet<>();
        for (final String authorStr : authors) {
            final String[] authorData = authorStr.split(" ");
            if (authorData.length != 3) {
                throw new IllegalArgumentException("Bad Authors Data. Proper format: FIRSTN_NAME LAST_NAME (YEAR)");
            }
            final String authorFirstName = authorData[0];
            final String authorLastName = authorData[1];
            final int authorBorn = Integer.parseInt(authorData[2]);
            AuthorEntity authorEntity = authorRepository.findByFirstnameAndLastnameAllIgnoreCase(authorFirstName, authorLastName);
            if (ObjectUtils.isEmpty(authorEntity)) {
                authorEntity = new AuthorEntity();
                authorEntity.setFirstname(authorFirstName);
                authorEntity.setLastname(authorLastName);
                authorEntity.setBirthDay(ZonedDateTime.now().withYear(authorBorn));
                authorEntities.add(authorRepository.save(authorEntity));
            } else {
                authorEntities.add(authorEntity);
            }
        }
        return authorEntities;
    }

    private Set<CategoryEntity> getCategories(Set<String> categories) {
        if(ObjectUtils.isEmpty(categories)){
            return Collections.emptySet();
        }
        final Set<CategoryEntity> categoryEntities = new HashSet<>();
        for (final String categoryName : categories) {
            CategoryEntity categoryEntity = categoryRepository.findCategoryByNameIgnoreCase(categoryName);
            if (ObjectUtils.isEmpty(categoryEntity)) {
                categoryEntity = new CategoryEntity();
                categoryEntity.setName(categoryName);
                categoryEntities.add(categoryRepository.save(categoryEntity));
            } else {
                categoryEntities.add(categoryEntity);
            }
        }
        return categoryEntities;
    }

    public void deleteBook(Long bookId) {
        booksRepository.deleteById(bookId);
    }
}
