package com.magda.library.persistance.repository;

import com.magda.library.persistance.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    AuthorEntity findByFirstnameAndLastnameAllIgnoreCase(final String firstname, final String lastname);
}
