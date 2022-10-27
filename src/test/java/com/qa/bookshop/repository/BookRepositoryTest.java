package com.qa.bookshop.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.qa.bookshop.entity.Book;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

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
    public void given_Book_To_Save_Should_Return_The_Saved_Book() {
        Book savedBook = bookRepository.save(book1);
        assertNotNull(savedBook);
        assertEquals("Peter", savedBook.getAuthor());
        assertEquals("Life of Peter", savedBook.getTitle());
    }

    @Test
    @DisplayName("get-book-non-existing-id-test")
    public void given_Non_Existing_Id_Should_Return_Optional_Empty() {
        bookRepository.save(book1);
        assertThat(bookRepository.findById(4)).isEmpty();
    }
}