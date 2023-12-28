package com.minhhieu.bookservice.repository;

import com.minhhieu.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b from Book b where concat(b.nameBook, ' ', b.genresBook) LIKE %?1%")
    public List<Book> search(String keyword);
}
