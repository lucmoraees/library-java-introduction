package main.java.com.library.UI;

import main.java.com.library.Entities.User;
import main.java.com.library.Utils.DependencyInjector;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserChatBot {

    private final DependencyInjector injector;
    private final User currentUser;
    private final Scanner scanner;

    public UserChatBot(DependencyInjector injector, User currentUser) {
        this.injector = injector;
        this.currentUser = currentUser;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Listar livros disponíveis");
            System.out.println("2. Pegar um livro emprestado");
            System.out.println("3. Devolver um livro");
            System.out.println("4. Listar seus empréstimos");
            System.out.println("5. Sair");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> listAvailableBooks();
                case 2 -> borrowBook();
                case 3 -> returnBook();
                case 4 -> listLoansByUser();
                case 5 -> {
                    System.out.println("Obrigado por utilizar o sistema de biblioteca. Até logo!");
                    running = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private void listAvailableBooks() {
        injector.getListAvailableBooksUseCase().execute()
                .forEach(book -> System.out.println("- " + book.getTitle() + " (ID: " + book.getId() + ")"));
    }

    private void borrowBook() {
        System.out.println("Informe o ID do livro:");
        String bookId = scanner.nextLine();

        injector.getBorrowBookUseCase().execute(currentUser.getId(), bookId);
        System.out.println("Livro emprestado com sucesso!");
    }

    private void returnBook() {
        System.out.println("Informe o ID do empréstimo:");
        String loanId = scanner.nextLine();

        injector.getReturnBookUseCase().execute(loanId);
        System.out.println("Livro devolvido com sucesso!");
    }

    private void listLoansByUser() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        injector.getGetLoansByUserUseCase().execute(currentUser.getId())
                .forEach(loan -> System.out.println("Empréstimo ID: " + loan.getId() +
                        " Início: " + loan.getStartDate().format(formatter) +
                        " Data entrega: " + loan.getEndDate().format(formatter)));
    }
}

