package main.java.com.library.UseCases.Users;

import main.java.com.library.Entities.User;

import java.util.Optional;

public interface GetUserByIdUseCase {
    Optional<User> execute(String userId);
}
