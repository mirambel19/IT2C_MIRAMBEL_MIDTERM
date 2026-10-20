package com.library.model;

public class ScenarioEntity {

    private int id;
    private String title;
    private String author;
    private String isbn;
    private String status;

    public ScenarioEntity(
            String title,
            String author,
            String isbn,
            String status) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getStatus() {
        return status;
    }
}