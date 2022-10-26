package com.qa.bookshop.controller;

import java.util.List;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.service.BookServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("book-service")
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    ResponseEntity<?> responseEntity;

    /*
     * End Points
     *  getAllBooks (GET)
     */
    @GetMapping("/get-all-books")
    public ResponseEntity<?> getAllBooks(){
        try {
            List<Book> bookList = this.bookService.getAllBooks();
            responseEntity = new ResponseEntity<>(bookList,HttpStatus.OK);
        } catch(Exception e) {
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
