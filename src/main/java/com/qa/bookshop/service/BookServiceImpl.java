package com.qa.bookshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.repository.BookRepository;
import com.qa.bookshop.exception.BookNotFoundException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

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