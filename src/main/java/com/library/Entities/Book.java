package main.java.com.library.Entities;

import java.util.UUID;

import static main.java.com.library.Utils.Constants.MAX_LOAN_DAYS;

public class Book {
    private String id;
    private String title;
    private String description;
    private int LoanDuration = MAX_LOAN_DAYS;

    public Book(String title, String description, int LoanDuration) {
        this.id = UUID.randomUUID().toString();;
        this.title = title;
        this.description = description;
        this.LoanDuration = LoanDuration;
    }

    public Book(String id, String title, String description, int LoanDuration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.LoanDuration = LoanDuration;
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

    public int getLoanDuration() {
        return LoanDuration;
    }

    public void setLoanDuration(int loanDuration) {
        this.LoanDuration = loanDuration;
    }
}
