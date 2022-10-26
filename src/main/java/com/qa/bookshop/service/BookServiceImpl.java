package com.qa.bookshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

	/*
	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	} */

    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }
}