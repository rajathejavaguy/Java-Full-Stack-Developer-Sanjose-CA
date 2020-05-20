package com.library.service;

import com.library.model.Book;

import java.rmi.server.ExportException;
import java.util.List;

public interface BookService {
    public Book getBook(Integer id) throws Exception;
    public Book updateBook(Book b, Integer id) throws Exception;
    public Book createBook(Book b) throws Exception;
    public List<Book> getAllBooks(Integer libraryId) throws Exception;
}
