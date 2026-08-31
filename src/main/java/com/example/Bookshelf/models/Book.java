package com.example.Bookshelf.models;

import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int pages;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne
    private User user;

    public enum Status{
        WANT_TO_READ, READING, FINISHED
    }

    public enum Genre{
        FICTION, NON_FICTION, SCIENCE, HISTORY, FANTASY, BIOGRAPHY
    }

    public Book(){}

    public Book(String title,
                String author,
                int pages,
                Status status,
                Genre genre){
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.status = status;
        this.genre = genre;
    }

    public Status getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getUser() {
        return user;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
