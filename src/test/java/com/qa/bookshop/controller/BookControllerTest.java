package com.qa.bookshop.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.qa.bookshop.entity.Book;
import com.qa.bookshop.service.BookService;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @Autowired
    @InjectMocks
    private BookController bookController;

    @Autowired
    MockMvc mockMvc;

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

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @AfterEach
    public void tearDown() {
        book1 = book2 = book3 = null;
        bookList = null;
    }

    @Test
    @DisplayName("save-book-test")
    public void given_Book_To_Save_Book_Should_Return_Book_As_JSON_With_Status_Created() throws Exception {
        when(bookService.saveBook(any())).thenReturn(book1);
        mockMvc.perform(post("/api/v1/book-service/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(book1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.author").value("Peter"));
    }

    public static String asJsonString(Object obj) {
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;

        // Try block to check for exceptions
        try {

            // Getting organisation object as a json string
            jsonStr = Obj.writeValueAsString(obj);

            // Displaying JSON String on console
            System.out.println(jsonStr);
        }

        // Catch block to handle exceptions
        catch (IOException e) {

            // Display exception along with line number
            // using printStackTrace() method
            e.printStackTrace();
        }
        return jsonStr;
    }
}