package com.example.Bookshelf.repostiories;

import com.example.Bookshelf.models.Book;
import com.example.Bookshelf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUser(User user);
    List<Book> findByUserAndStatus(User user, Book.Status status);
    List<Book> findByUserAndGenre(User user, Book.Genre genre);
}
