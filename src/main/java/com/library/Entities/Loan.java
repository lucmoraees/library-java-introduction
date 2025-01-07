package main.java.com.library.Entities;

import java.time.LocalDateTime;
import java.util.UUID;

public class Loan {
    private String id;
    private String userId;
    private String bookId;
    private boolean active;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Loan(String userId, String bookId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.bookId = bookId;
        this.active = true;
        this.startDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        this.active = false;
    }
}
