package com.qa.bookshop.service;

import java.util.List;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.exception.BookAlreadyExistsException;
import com.qa.bookshop.exception.BookNotFoundException;

public interface BookService {
    public Book saveBook(Book book) throws BookAlreadyExistsException;
    List<Book> getAllBooks();
    Book getBookById(int id) throws BookNotFoundException;
}
