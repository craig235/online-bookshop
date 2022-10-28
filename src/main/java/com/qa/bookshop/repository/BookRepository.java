package com.qa.bookshop.repository;

import com.qa.bookshop.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query(value = "select * from t_book where author like CONCAT('%', :author, '%')", nativeQuery = true)
    List<Book> findByAuthor(String author);

    Book findByTitle(String title);
}