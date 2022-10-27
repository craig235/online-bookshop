package com.qa.bookshop.service;

import java.util.List;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.exception.BookNotFoundException;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(int id) throws BookNotFoundException;
}
