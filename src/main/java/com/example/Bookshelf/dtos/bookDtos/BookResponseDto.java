package com.example.Bookshelf.dtos.bookDtos;

import com.example.Bookshelf.models.Book;

public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private int pages;
    private Book.Status status;
    private Book.Genre genre;

    public BookResponseDto(Long id,
                           String title,
                           String author,
                           int pages,
                           Book.Status status,
                           Book.Genre genre){
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.status = status;
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages() {
        return pages;
    }

    public Book.Genre getGenre() {
        return genre;
    }

    public Book.Status getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setGenre(Book.Genre genre) {
        this.genre = genre;
    }
}
