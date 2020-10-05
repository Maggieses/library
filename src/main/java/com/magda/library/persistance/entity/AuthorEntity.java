package com.magda.library.persistance.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.ZonedDateTime;

@Entity(name = "author")
public class AuthorEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private ZonedDateTime birthDay;
    @OneToOne
    private AuthorBiographyEntity authorBiographyEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public ZonedDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(ZonedDateTime birthDay) {
        this.birthDay = birthDay;
    }

    public AuthorBiographyEntity getAuthorBiographyEntity() {
        return authorBiographyEntity;
    }

    public void setAuthorBiographyEntity(AuthorBiographyEntity authorBiographyEntity) {
        this.authorBiographyEntity = authorBiographyEntity;
    }
}
