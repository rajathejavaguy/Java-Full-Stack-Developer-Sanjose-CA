DROP TABLE if exists library_book;
DROP TABLE if exists book;
DROP TABLE if exists library;
CREATE TABLE book (
    bookid INT AUTO_INCREMENT PRIMARY KEY,
    bookname VARCHAR(250) NOT NULL,
    author VARCHAR(250)
);

CREATE TABLE library (
    libraryid INT AUTO_INCREMENT PRIMARY KEY,
    libraryname VARCHAR(250) NOT NULL
);

CREATE TABLE library_book(
    libraryid INT NOT NULL,
    bookid INT NOT NULL,
    FOREIGN KEY (libraryid) REFERENCES library(libraryid),
    FOREIGN KEY (bookid) REFERENCES book(bookid),
    PRIMARY KEY (libraryid, bookid)
);

INSERT INTO library VALUES (2001, 'DownTown Library');
INSERT INTO library VALUES (2002, 'Suburb Library');

INSERT INTO book VALUES (1001, 'Basics of JAVA8', 'Rajkumar G');
INSERT INTO book VALUES (1002, 'Basics of C', 'Ramya G');
INSERT INTO book VALUES (1003, 'Basics of .NET', 'Ramkumar G');

INSERT INTO library_book VALUES (2001, 1001);
INSERT INTO library_book VALUES (2002, 1002);
INSERT INTO library_book VALUES (2001, 1003);
INSERT INTO library_book VALUES (2002, 1003);
