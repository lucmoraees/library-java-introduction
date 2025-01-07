package main.java.com.library.Entities;

public enum UserLevel {
    ADMIN("Admin"),
    STANDARD("Standard");

    private final String displayName;

    UserLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
