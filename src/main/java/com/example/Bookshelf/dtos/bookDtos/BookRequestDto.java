package com.example.Bookshelf.dtos.bookDtos;

import com.example.Bookshelf.models.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookRequestDto {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    @NotBlank(message = "Author cannot be blank")
    private String author;
    @Positive(message = "Pages must be above 0")
    private int pages;
    @NotNull(message = "Status cannot be null")
    private Book.Status status;
    @NotNull(message = "Genre cannot be blank")
    private Book.Genre genre;

    public Book.Status getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public Book.Genre getGenre() {
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() {
        return pages;
    }
}
