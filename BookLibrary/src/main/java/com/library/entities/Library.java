package com.library.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "library")
public class Library {
    @Id
    @Column(name = "libraryid")
    private Integer libraryId;

    @Column(name = "libraryname")
    private String libraryName;

    @ManyToMany
    @JoinTable(
        name = "library_book",
        joinColumns = @JoinColumn(name = "libraryid"),
        inverseJoinColumns = @JoinColumn(name = "bookid")
    )
    private Set<Book> books;

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryid) {
        this.libraryId = libraryid;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryname) {
        this.libraryName = libraryname;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
