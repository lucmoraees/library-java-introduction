package main.java.com.library.UseCases.Users.impl;

import main.java.com.library.Repositories.UserRepository;
import main.java.com.library.UseCases.Users.CheckIfUserIsAdminUseCase;

public class CheckIfUserIsAdminUseCaseImpl implements CheckIfUserIsAdminUseCase {

    private final UserRepository userRepository;

    public CheckIfUserIsAdminUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean execute(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo ou vazio.");
        }

        boolean isAdmin = userRepository.isAdmin(userId);

        if (isAdmin) {
            System.out.println("O usuário com ID '" + userId + "' é um admin.");
        } else {
            System.out.println("O usuário com ID '" + userId + "' não é um admin.");
        }

        return isAdmin;
    }
}
