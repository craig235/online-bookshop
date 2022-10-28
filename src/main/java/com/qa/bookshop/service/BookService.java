package com.qa.bookshop.service;

import java.util.List;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.exception.BookAlreadyExistsException;
import com.qa.bookshop.exception.BookNotFoundException;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(int id) throws BookNotFoundException;
    public Book addBook(Book book) throws BookAlreadyExistsException;
    public Book updateBook(Book book) throws BookNotFoundException;
    public boolean deleteBook(int id) throws BookNotFoundException;
    public List<Book> findByAuthor(String author) throws BookNotFoundException;
}
