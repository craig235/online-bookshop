package com.qa.bookshop.service;

import java.util.List;
import java.util.Optional;

import com.qa.bookshop.exception.BookAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.repository.BookRepository;
import com.qa.bookshop.exception.BookNotFoundException;

@Service("BookServiceImpl")
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) throws BookAlreadyExistsException {

        Book createdBook = null;
        /*
         * 1. Check whether book already exists
         * 2. If yes, throw BookAlreadyExistsException
         * 3. If no, save book object to database
         * 4. Return the created book object
         */

        Book bookByName = this.bookRepository.findByTitle(book.getTitle());
        if(bookByName != null) {
            throw new BookAlreadyExistsException();
        } else {
            createdBook = this.bookRepository.save(book);
        }

        return createdBook;
    }

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) throws BookNotFoundException {
        Optional<Book> findByIdOptional = this.bookRepository.findById(id);
        if (findByIdOptional.isEmpty()) {
            throw new BookNotFoundException();
        }
        return findByIdOptional.get();
    }
}