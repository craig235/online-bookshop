package com.qa.bookshop.controller;

import java.util.List;

import com.qa.bookshop.exception.BookAlreadyExistsException;
import com.qa.bookshop.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.exception.BookNotFoundException;

@RestController
@RequestMapping("api/v1/book-service")
public class BookController {
    @Autowired
    //@Qualifier("BookServiceImpl")
    BookService bookService;

    ResponseEntity<?> responseEntity;

    @PostMapping("books")
    public ResponseEntity<?> saveBook(@RequestBody Book book) throws BookAlreadyExistsException {
        ResponseEntity<?> responseEntity = null;

        try {
            Book createdBook = this.bookService.saveBook(book);
            responseEntity = new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } catch (BookAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    /*
     * End Points
     *  getAllBooks (GET)
     */
    @GetMapping("books")
    public ResponseEntity<?> getAllBooks() {
        try {
            List<Book> bookList = this.bookService.getAllBooks();
            responseEntity = new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @GetMapping("books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") int id) throws BookNotFoundException {
        try {
            Book employee = this.bookService.getBookById(id);
            responseEntity = new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}