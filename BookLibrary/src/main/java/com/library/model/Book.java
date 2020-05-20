package com.library.model;

public class Book {
    private Integer bookId;
    private String bookName;
    private Integer libraryId;

    public Integer getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Integer libraryId) {
        this.libraryId = libraryId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookid) {
        this.bookId = bookid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
