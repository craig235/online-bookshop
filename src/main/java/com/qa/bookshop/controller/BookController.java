package com.qa.bookshop.controller;

import java.util.List;
import javax.validation.constraints.Min;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.exception.BookNotFoundException;
import com.qa.bookshop.exception.BookAlreadyExistsException;
import com.qa.bookshop.service.BookService;

@RestController
@RequestMapping("api/v1/book-service")
public class BookController {
    @Autowired
    //@Qualifier("BookServiceImpl")
    BookService bookService;

    ResponseEntity<?> responseEntity;

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
            Book book = this.bookService.getBookById(id);
            responseEntity = new ResponseEntity<>(book, HttpStatus.OK);
        } catch (BookNotFoundException e) {
            throw e;
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PostMapping("books")
    public ResponseEntity<?> addBook(@RequestBody Book book) throws BookAlreadyExistsException {
        try {
            Book createdBook = this.bookService.addBook(book);
            responseEntity = new ResponseEntity<>(createdBook, HttpStatus.CREATED);
        } catch (BookAlreadyExistsException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("books")
    public ResponseEntity<?> updateBook(@RequestBody Book book) throws BookNotFoundException{
        try {
            Book updatedBook = this.bookService.updateBook(book);
            responseEntity = new ResponseEntity<>(updatedBook,HttpStatus.OK);
        } catch(BookNotFoundException e) {
            throw e;
        }catch(Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") @Min(value = 0,message = "id should be greater than 0")  int id) throws BookNotFoundException{
        try {
            boolean status = this.bookService.deleteBook(id);
            if(status)
                responseEntity = new ResponseEntity<>("Book deleted successfully.",HttpStatus.OK);
        } catch(BookNotFoundException e) {
            throw e;
        }catch(Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("books/author/{author}")
    public ResponseEntity<?> findByAuthor(@PathVariable("author") String author){
        try {
            List<Book> bookListByAuthor = this.bookService.findByAuthor(author);
            responseEntity = new ResponseEntity<>(bookListByAuthor,HttpStatus.OK);
        } catch(Exception e) {
            responseEntity = new ResponseEntity<>("An internal error occurred. Please try again.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}