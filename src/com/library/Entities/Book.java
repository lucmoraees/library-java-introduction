package com.library.Entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Book {
    private String id;
    private String title;
    private String description;
    private LocalDateTime loanStartDate;

    public Book() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate() {
        this.loanStartDate = LocalDateTime.now();
    }
}
