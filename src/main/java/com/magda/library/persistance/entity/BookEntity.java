package com.magda.library.persistance.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private ZonedDateTime releaseDate;
    @OneToOne
    private BookDescription description;

    @ManyToMany
    private Set<AuthorEntity> authorEntities;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<CategoryEntity> categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ZonedDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BookDescription getDescription() {
        return description;
    }

    public void setDescription(BookDescription description) {
        this.description = description;
    }

    public Set<AuthorEntity> getAuthorEntities() {
        return authorEntities;
    }

    public void setAuthorEntities(Set<AuthorEntity> authorEntities) {
        this.authorEntities = authorEntities;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }
}
