package main.java.com.library.UI;

import main.java.com.library.Entities.Book;
import main.java.com.library.Entities.User;
import main.java.com.library.Entities.UserLevel;
import main.java.com.library.Utils.DependencyInjector;

import java.util.List;
import java.util.Scanner;

public class AdminChatBot {

    private final DependencyInjector injector;
    private final User currentUser;
    private final Scanner scanner;

    public AdminChatBot(DependencyInjector injector, User currentUser) {
        this.injector = injector;
        this.currentUser = currentUser;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Listar livros disponíveis");
            System.out.println("2. Cadastrar um novo livro");
            System.out.println("3. Alterar tempo de empréstimo de um livro");
            System.out.println("4. Cadastrar um novo usuário");
            System.out.println("5. Listar usuários cadastrados");
            System.out.println("6. Deletar usuário");
            System.out.println("7. Sair");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> listAvailableBooks();
                case 2 -> addNewBook();
                case 3 -> updateLoanDuration();
                case 4 -> addUser();
                case 5 -> getAllUsers();
                case 6 -> deleteUser();
                case 7 -> {
                    System.out.println("Obrigado por utilizar o sistema de biblioteca. Até logo!");
                    running = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listAvailableBooks() {
        injector.getListAvailableBooksUseCase().execute()
                .forEach(book -> System.out.println("- " + book.getTitle() + " (ID: " + book.getId() + "): " + book.getDescription()));
    }

    private void addNewBook() {
        System.out.println("Informe o título do livro:");
        String title = scanner.nextLine();

        System.out.println("Informe a descrição do livro:");
        String description = scanner.nextLine();

        System.out.println("Informe o tempo de empréstimo padrão (em dias):");
        int loanDuration = Integer.parseInt(scanner.nextLine());

        injector.getAddBookUseCase().execute(new Book(title, description, loanDuration));
        System.out.println("Livro cadastrado com sucesso!");
    }

    private void updateLoanDuration() {
        System.out.println("Informe o ID do livro:");
        String bookId = scanner.nextLine();

        System.out.println("Informe o novo tempo de empréstimo (em dias):");
        int days = Integer.parseInt(scanner.nextLine());

        injector.getUpdateLoanDurationUseCase().execute(bookId, days);
    }

    private void addUser() {
        System.out.println("Informe o nome do novo usuário:");
        String name = scanner.nextLine();

        System.out.println("Informe o username do novo usuário:");
        String username = scanner.nextLine();

        injector.getRegisterUserUseCase().execute(new User(name, username, UserLevel.STANDARD));
        System.out.println("Usuário '" + username + "' cadastrado com sucesso!");
    }

    public void getAllUsers() {
        List<User> users = injector.getGetAllUsersUseCase().execute();

        for (User user : users) {
            System.out.println(user.getName() + " - " + user.getLevel() + " - " + user.getUsername() + " - " + user.getId());
        }
    }

    private void deleteUser() {
        System.out.println("Informe o id usuário:");
        String userId = scanner.nextLine();

        injector.getDeleteUserUseCase().execute(userId);
    }
}

