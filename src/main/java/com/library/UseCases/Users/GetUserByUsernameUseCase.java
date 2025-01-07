package main.java.com.library.UseCases.Users;

import main.java.com.library.Entities.User;

import java.util.Optional;

public interface GetUserByUsernameUseCase {
    Optional<User> execute(String username);
}
