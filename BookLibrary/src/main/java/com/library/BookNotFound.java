package com.library;

public class BookNotFound extends Exception {
    public BookNotFound(){
        super();
    }

    public BookNotFound(String msg) {
        super(msg);
    }
}
