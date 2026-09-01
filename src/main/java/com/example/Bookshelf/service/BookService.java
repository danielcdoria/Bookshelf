package com.example.Bookshelf.service;

import com.example.Bookshelf.dtos.bookDtos.BookRequestDto;
import com.example.Bookshelf.dtos.bookDtos.BookResponseDto;
import com.example.Bookshelf.models.Book;
import com.example.Bookshelf.models.User;
import com.example.Bookshelf.repostiories.BookRepository;
import com.example.Bookshelf.repostiories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final UserRepository userRepository;
    private final BookRepository repository;
    public BookService(UserRepository userRepository,
                       BookRepository repository){
        this.userRepository = userRepository;
        this.repository = repository;
    }

    public User getLoggedUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public BookResponseDto convertToDto(Book book){
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPages(),
                book.getStatus(),
                book.getGenre()
        );
    }

    public List<BookResponseDto> list(){
        return repository.findByUser(getLoggedUser())
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public BookResponseDto create(BookRequestDto dto){
        User user = getLoggedUser();
        Book book = new Book(
                dto.getTitle(),
                dto.getAuthor(),
                dto.getPages(),
                dto.getStatus(),
                dto.getGenre()
        );
        book.setUser(user);
        repository.save(book);
        return convertToDto(book);
    }

    public BookResponseDto findById(Long id){
        User user = getLoggedUser();
        Book book = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if (!book.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("Acess denied.");
        }
        return convertToDto(book);
    }

    public String remove(Long id){
        User user = getLoggedUser();
        Book book = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if (!book.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("Acess denied.");
        }
        repository.delete(book);
        return "The book was removed successfully!";
    }

    public List<BookResponseDto> findByStatus(Book.Status status){
        User user = getLoggedUser();
        return repository.findByUserAndStatus(user, status)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public List<BookResponseDto> findByGenre(Book.Genre genre){
        User user = getLoggedUser();
        return repository.findByUserAndGenre(user, genre)
                .stream()
                .map(this::convertToDto)
                .toList();
    }
}
