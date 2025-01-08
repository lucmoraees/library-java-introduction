package main.java.com.library.Utils;


import main.java.com.library.Repositories.LoanRepository;
import main.java.com.library.Repositories.UserRepository;
import main.java.com.library.Repositories.BookRepository;
import main.java.com.library.Repositories.impl.BookRepositoryImpl;
import main.java.com.library.Repositories.impl.LoanRepositoryImpl;
import main.java.com.library.Repositories.impl.UserRepositoryImpl;
import main.java.com.library.UseCases.Books.*;
import main.java.com.library.UseCases.Books.impl.*;
import main.java.com.library.UseCases.Loans.*;
import main.java.com.library.UseCases.Loans.impl.*;
import main.java.com.library.UseCases.Users.*;
import main.java.com.library.UseCases.Users.impl.*;

public class DependencyInjector {

    // Repositórios
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    // Use Cases - Books
    private final ListAvailableBooksUseCase listAvailableBooksUseCase;
    private final AddBookUseCase addBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;
    private final UpdateLoanDurationUseCase updateLoanDurationUseCase;

    // Use Cases - Loans
    private final BorrowBookUseCase borrowBookUseCase;
    private final ReturnBookUseCase returnBookUseCase;
    private final GetLoansByUserUseCase getLoansByUserUseCase;

    // Use Cases - Users
    private final RegisterUserUseCase registerUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetUserByUsernameUseCase getUserByUsernameUseCase;
    private final CheckIfUserIsAdminUseCase checkIfUserIsAdminUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    // Singleton Instance
    private static DependencyInjector instance;

    private DependencyInjector() {
        // Inicializa os repositórios
        this.userRepository = new UserRepositoryImpl();
        this.bookRepository = new BookRepositoryImpl();
        this.loanRepository = new LoanRepositoryImpl();

        // Inicializa os use cases - Books
        this.listAvailableBooksUseCase = new ListAvailableBooksUseCaseImpl(bookRepository);
        this.addBookUseCase = new AddBookUseCaseImpl(bookRepository);
        this.deleteBookUseCase = new DeleteBookUseCaseImpl(bookRepository);
        this.updateLoanDurationUseCase = new UpdateLoanDurationUseCaseImpl(bookRepository);

        // Inicializa os use cases - Loans
        this.borrowBookUseCase = new BorrowBookUseCaseImpl(loanRepository, userRepository, bookRepository);
        this.returnBookUseCase = new ReturnBookUseCaseImpl(loanRepository);
        this.getLoansByUserUseCase = new GetLoansByUserUseCaseImpl(loanRepository);

        // Inicializa os use cases - Users
        this.registerUserUseCase = new RegisterUserUseCaseImpl(userRepository);
        this.getUserByIdUseCase = new GetUserByIdUseCaseImpl(userRepository);
        this.getUserByUsernameUseCase = new GetUserByUsernameUseCaseImpl(userRepository);
        this.checkIfUserIsAdminUseCase = new CheckIfUserIsAdminUseCaseImpl(userRepository);
        this.getAllUsersUseCase = new GetAllUsersUseCaseImpl(userRepository);
        this.deleteUserUseCase = new DeleteUserUseCaseImpl(userRepository);
    }

    // Método para obter a instância única do DependencyInjector (Singleton)
    public static DependencyInjector getInstance() {
        if (instance == null) {
            instance = new DependencyInjector();
        }
        return instance;
    }

    // Métodos públicos para obter as instâncias dos use cases

    // Books
    public ListAvailableBooksUseCase getListAvailableBooksUseCase() {
        return listAvailableBooksUseCase;
    }

    public AddBookUseCase getAddBookUseCase() {
        return addBookUseCase;
    }

    public DeleteBookUseCase getDeleteBookUseCase() {
        return deleteBookUseCase;
    }

    public UpdateLoanDurationUseCase getUpdateLoanDurationUseCase() {
        return updateLoanDurationUseCase;
    }

    // Loans
    public BorrowBookUseCase getBorrowBookUseCase() {
        return borrowBookUseCase;
    }

    public ReturnBookUseCase getReturnBookUseCase() {
        return returnBookUseCase;
    }

    public GetLoansByUserUseCase getGetLoansByUserUseCase() {
        return getLoansByUserUseCase;
    }

    // Users
    public RegisterUserUseCase getRegisterUserUseCase() {
        return registerUserUseCase;
    }

    public GetUserByIdUseCase getGetUserByIdUseCase() {
        return getUserByIdUseCase;
    }

    public GetUserByUsernameUseCase getGetUserByUsernameUseCase() {
        return getUserByUsernameUseCase;
    }

    public CheckIfUserIsAdminUseCase getCheckIfUserIsAdminUseCase() {
        return checkIfUserIsAdminUseCase;
    }

    public GetAllUsersUseCase getGetAllUsersUseCase() {
        return getAllUsersUseCase;
    }

    public DeleteUserUseCase getDeleteUserUseCase() {
        return deleteUserUseCase;
    }
}

