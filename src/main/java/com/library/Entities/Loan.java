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

    public Loan() {
        this.id = UUID.randomUUID().toString();
    }

    public Loan(String id, String userId, String bookId, boolean active, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.active = active;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
