package main.java.com.library.Entities;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String username;
    private UserLevel level;

    public User() {
        this.id = UUID.randomUUID().toString();
    }
    
    public User(String id, String name, String username, UserLevel level) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.level = level;
    }

    public User(String name, String username, UserLevel level) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.username = username;
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }
}
