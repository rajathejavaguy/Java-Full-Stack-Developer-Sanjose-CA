package com.library.controller;

import com.library.model.Book;
import com.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Integer id) throws Exception {
        try {
            log.info("Entered inside Book Controller getBook : " + id);
            return bookService.getBook(id);
        } catch (Exception e) {
            log.error("Exception while fetching the book with id : " + id);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public Book putBook(@RequestBody Book book, @PathVariable Integer id) throws Exception {
        try {
            log.info("Entered inside Book Controller putBook : " + id);
            return bookService.updateBook(book, id);
        } catch (Exception e) {
            log.error("Exception while fetching the book with id : " + id);
            throw e;
        }
    }

    @PostMapping
    public Book createBook(@RequestBody Book b) throws Exception {
        try {
            log.info("Entered inside Book Controller createBook : " + b.getBookId());
            return bookService.createBook(b);
        } catch (Exception e) {
            log.error("Exception while fetching the book with id : " + b.getBookId());
            throw e;
        }
    }

    @GetMapping("/library/{libraryId}")
    public List<Book> getAllBooks(@PathVariable Integer libraryId) throws Exception {
        log.info("Entered inside Book Controller getAllBooks : " + libraryId);
        try {
            return bookService.getAllBooks(libraryId);
        } catch (Exception e) {
            log.error("Exception while fetching the books for Library id : " + libraryId);
            throw e;
        }
    }
}
