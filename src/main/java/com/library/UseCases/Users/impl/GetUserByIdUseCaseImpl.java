package main.java.com.library.UseCases.Users.impl;

import main.java.com.library.Entities.User;
import main.java.com.library.Repositories.UserRepository;
import main.java.com.library.UseCases.Users.GetUserByIdUseCase;

import java.util.Optional;

public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {

    private final UserRepository userRepository;

    public GetUserByIdUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> execute(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo ou vazio.");
        }

        Optional<User> user = userRepository.getUserById(userId);

        if (user.isEmpty()) {
            System.out.println("Nenhum usuário encontrado com o ID '" + userId + "'.");
        } else {
            System.out.println("Usuário encontrado: " + user.get().getName());
        }

        return user;
    }
}
