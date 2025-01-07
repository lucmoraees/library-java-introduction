package main.java.com.library.Repositories;

import main.java.com.library.Entities.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);
    void delete(User user);
    List<User> getUsers();
    Optional<User> getUserById(String id);
    Optional<User> getUserByUserName(String userName);
    boolean isAdmin(String userId);
}
