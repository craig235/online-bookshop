package com.qa.bookshop.service;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.repository.BookRepository;
import com.qa.bookshop.exception.BookAlreadyExistsException;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock //creates the Mock Object
    private BookRepository bookRepository;

    @Autowired
    @InjectMocks
    private BookServiceImpl bookService;

    Book book1;
    Book book2;
    Book book3;

    List<Book> bookList;

    @BeforeEach
    public void setUp() {
        /*
         * Create the necessary instances
         * Create dummy data
         */
        book1 = new Book(1,"Peter","Life of Peter");
        book2 = new Book(2,"Bob","Life of Bob");
        book3 = new Book(3,"Mary","Life of Mary");
        bookList = Arrays.asList(book1,book2,book3);
    }

    @AfterEach
    public void tearDown() {
        book1 = book2 = book3 = null;
        bookRepository.deleteAll();
        bookList = null;
    }

    @Test
    @DisplayName("save-book-test")
    public void given_Book_To_Save_Should_Return_The_Saved_Book() throws BookAlreadyExistsException {
        when(bookRepository.findByTitle(any())).thenReturn(null);
        when(bookRepository.save(any())).thenReturn(book1);
        Book savedBook = bookService.saveBook(book1);
        assertNotNull(savedBook);
        assertEquals(1,savedBook.getId());
        verify(bookRepository,times(1)).findByTitle(any());
        verify(bookRepository,times(1)).save(any());
    }

    @Test
    @DisplayName("save-book-throws-exception-test")
    public void given_Existing_Book_To_Save_Method_Should_Throw_Exception() throws BookAlreadyExistsException {
        when(bookRepository.findByTitle(any())).thenReturn(book1);

        //empService.saveEmployee(emp1);
        assertThrows(BookAlreadyExistsException.class,()-> bookService.saveBook(book1));
    }
}