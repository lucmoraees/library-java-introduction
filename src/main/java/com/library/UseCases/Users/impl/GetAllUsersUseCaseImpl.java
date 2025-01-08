package main.java.com.library.UseCases.Users.impl;

import main.java.com.library.Entities.User;
import main.java.com.library.Repositories.UserRepository;
import main.java.com.library.UseCases.Users.GetAllUsersUseCase;

import java.util.List;

public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {

    private final UserRepository userRepository;

    public GetAllUsersUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> execute() {
        return userRepository.getUsers();
    }
}
