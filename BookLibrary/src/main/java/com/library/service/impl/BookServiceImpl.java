package com.library.service.impl;

import com.library.BookNotFound;
import com.library.InvalidIdException;
import com.library.entities.Library;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.repository.LibraryRepository;
import com.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private LibraryRepository libraryRepo;

    @Override
    public Book getBook(Integer id) throws Exception {
        log.info("Entered inside Book Service : " + id);
        Book b = new Book();
        if(Optional.ofNullable(id).orElse(0) > 0) {
            Optional<com.library.entities.Book> ob = bookRepo.findById(id);
            if(ob.isPresent()) {
                BeanUtils.copyProperties(ob.get(), b);
                return b;
            } else {
                log.error("No book with id : " + id);
                throw new BookNotFound("No book with id : " + id);
            }
        } else {
            log.error("Book id : " + id + " is invalid");
            throw new InvalidIdException("Book id : " + id + " is invalid");
        }
    }

    @Override
    public Book updateBook(Book b, Integer id) throws Exception {
        log.info("Entered inside Book Service updateBook: " + id);
        if(Optional.ofNullable(id).orElse(0) > 0) {
            Optional<com.library.entities.Book> ob = bookRepo.findById(id);
            if(ob.isPresent()) {
                com.library.entities.Book entity = ob.get();
                BeanUtils.copyProperties(b, entity);
                bookRepo.save(entity);
                return b;
            } else {
                log.error("No book with id : " + id);
                throw new BookNotFound("No book with id : " + id);
            }
        } else {
            log.error("Book id : " + id + " is invalid");
            throw new InvalidIdException("Book id : " + id + " is invalid");
        }
    }

    @Override
    public Book createBook(Book b) throws Exception {
        log.info("Entered inside Book Service createBook: " + b.getBookId());
        if(Optional.ofNullable(b.getBookId()).orElse(0) > 0) {
            Optional<com.library.entities.Book> ob = bookRepo.findById(b.getBookId());
            com.library.entities.Book entity = new com.library.entities.Book();
            if(ob.isPresent()) {
                log.error("Book with id : " + b.getBookId() + " already existed.");
                throw new BookNotFound("Book with id : " + b.getBookId() + " already existed.");
            } else {
                Set<Library> l = new HashSet<>();
                Library entityL = libraryRepo.findById(b.getLibraryId()).get();
                l.add(entityL);
                entity.setLibraries(l);
                BeanUtils.copyProperties(b, entity);
                entityL.getBooks().add(entity);
                bookRepo.save(entity);
                return b;
            }
        } else {
            log.error("Book id : " + b.getBookId() + " should be present for new book");
            throw new InvalidIdException("Book id : " + b.getBookId() + " should be present for new book");
        }
    }

    @Override
    public List<Book> getAllBooks(Integer libraryId) throws Exception {
        log.info("Entered inside Book Service getAllBooks : " + libraryId);
        List<Book> books = new ArrayList<>();
        if(Optional.ofNullable(libraryId).orElse(0) > 0) {
            Optional<com.library.entities.Library> ol = libraryRepo.findById(libraryId);
            if(ol.isPresent()) {
                ol.get().getBooks().forEach(s -> {
                    Book b = new Book();
                    BeanUtils.copyProperties(s, b);
                    books.add(b);
                });
                return books;
            } else {
                log.error("No library with id : " + libraryId);
                throw new BookNotFound("No library with id : " + libraryId);
            }
        } else {
            log.error("Library id : " + libraryId + " is invalid");
            throw new InvalidIdException("Library id : " + libraryId + " is invalid");
        }
    }
}
