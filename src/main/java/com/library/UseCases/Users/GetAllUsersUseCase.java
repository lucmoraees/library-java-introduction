package main.java.com.library.UseCases.Users;

import main.java.com.library.Entities.User;

import java.util.List;

public interface GetAllUsersUseCase {
    List<User> execute();
}
