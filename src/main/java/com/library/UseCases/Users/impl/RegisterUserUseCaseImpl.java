package main.java.com.library.UseCases.Users.impl;

import main.java.com.library.Entities.User;
import main.java.com.library.Repositories.UserRepository;
import main.java.com.library.UseCases.Users.RegisterUserUseCase;

public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;

    public RegisterUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(User user) {
        if (user == null || user.getUsername() == null || user.getName() == null) {
            throw new IllegalArgumentException("Os dados do usuário não podem ser nulos.");
        }
        userRepository.save(user);
        System.out.println("Usuário '" + user.getUsername() + "' cadastrado com sucesso.");
    }
}
