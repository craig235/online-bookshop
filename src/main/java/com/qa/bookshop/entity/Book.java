package com.qa.bookshop.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @NotNull
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters only")
    @Pattern(regexp = "^[A-Za-z ]*", message = "Invalid author, must contain only alphanumeric")
    @Column(name = "book_author")
    private String author;

    @NotNull
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters only")
    @Pattern(regexp = "^[A-Za-z ]*", message = "Invalid title, must contain only alphanumeric")
    @Column(name = "book_title")
    private String title;
}
