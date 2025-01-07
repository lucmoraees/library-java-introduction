package main.java.com.library.UseCases.Users.impl;

import main.java.com.library.Entities.User;
import main.java.com.library.Repositories.UserRepository;
import main.java.com.library.UseCases.Users.GetUserByUsernameUseCase;

import java.util.Optional;

public class GetUserByUsernameUseCaseImpl implements GetUserByUsernameUseCase {

    private final UserRepository userRepository;

    public GetUserByUsernameUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> execute(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("O nome de usuário não pode ser nulo ou vazio.");
        }

        Optional<User> user = userRepository.getUserByUserName(username);

        if (user.isEmpty()) {
            System.out.println("Usuário com nome de usuário '" + username + "' não encontrado.");
        } else {
            System.out.println("Usuário com nome de usuário '" + username + "' encontrado: " + user.get().getName());
        }

        return user;
    }
}
