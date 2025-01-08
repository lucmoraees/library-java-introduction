package main.java.com.library.UseCases.Users.impl;

import main.java.com.library.Entities.User;
import main.java.com.library.Repositories.UserRepository;
import main.java.com.library.UseCases.Users.DeleteUserUseCase;

import java.util.Optional;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(String userId) {
        Optional<User> user = userRepository.getUserById(userId);

        if (!user.isPresent()) {
            throw new RuntimeException("User not found!");
        }

        userRepository.delete(userId);
    }
}
