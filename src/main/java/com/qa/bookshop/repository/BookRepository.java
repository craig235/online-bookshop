package com.qa.bookshop.repository;

import com.qa.bookshop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByAuthor(String name);
    Book findByTitle(String title);
}