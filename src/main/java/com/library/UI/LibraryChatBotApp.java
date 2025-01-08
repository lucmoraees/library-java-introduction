package main.java.com.library.UI;

import main.java.com.library.Entities.User;
import main.java.com.library.Entities.UserLevel;
import main.java.com.library.UseCases.Users.GetUserByUsernameUseCase;
import main.java.com.library.Utils.DependencyInjector;

import java.util.Optional;
import java.util.Scanner;

public class LibraryChatBotApp {

    private final DependencyInjector injector;
    private final Scanner scanner;
    private Optional<User> currentUser;

    public LibraryChatBotApp() {
        this.injector = DependencyInjector.getInstance();
        this.scanner = new Scanner(System.in);
        this.currentUser = Optional.empty();
    }

    public static void main(String[] args) {
        LibraryChatBotApp app = new LibraryChatBotApp();
        app.start();
    }

    public void start() {
        System.out.println("Bem-vindo ao sistema de biblioteca!");
        authenticateUser();

        if (isAdmin()) {
            AdminChatBot adminChatBot = new AdminChatBot(injector, currentUser.get());
            adminChatBot.start();
        } else {
            UserChatBot userChatBot = new UserChatBot(injector, currentUser.get());
            userChatBot.start();
        }
    }

    private void authenticateUser() {
        System.out.println("Informe seu username para iniciar:");
        String username = scanner.nextLine();

        GetUserByUsernameUseCase getUserByUsernameUseCase = injector.getGetUserByUsernameUseCase();
        currentUser = getUserByUsernameUseCase.execute(username);

        if (currentUser.isEmpty()) {
            System.out.println("Usuário não encontrado. Criando um novo usuário.");
            registerNewUser(username);
        } else {
            System.out.println("Bem-vindo, " + currentUser.get().getName() + "!");
        }
    }

    private boolean isAdmin() {
        return currentUser.isPresent() && currentUser.get().getLevel() == UserLevel.ADMIN;
    }

    private void registerNewUser(String username) {
        System.out.println("Informe seu nome:");
        String name = scanner.nextLine();

        User user = new User(name, username, UserLevel.STANDARD);
        injector.getRegisterUserUseCase().execute(user);
        currentUser = Optional.of(user);
    }
}

