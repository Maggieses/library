package com.magda.library.persistance.repository;

import com.magda.library.persistance.entity.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends CrudRepository<BookEntity, Long> {

    List<BookEntity> findAll();

}
