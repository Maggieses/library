package com.magda.library.persistance.repository;

import com.magda.library.persistance.entity.BookDescription;
import org.springframework.data.repository.CrudRepository;

public interface BookDescriptionRepository extends CrudRepository<BookDescription, Long> {
}
