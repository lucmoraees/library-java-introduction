package main.java.com.library.Utils;

import main.java.com.library.Entities.Book;
import main.java.com.library.Entities.User;
import main.java.com.library.Entities.UserLevel;
import main.java.com.library.Repositories.BookRepository;
import main.java.com.library.Repositories.LoanRepository;
import main.java.com.library.Repositories.UserRepository;
import main.java.com.library.Repositories.impl.BookRepositoryImpl;
import main.java.com.library.Repositories.impl.LoanRepositoryImpl;
import main.java.com.library.Repositories.impl.UserRepositoryImpl;
import main.java.com.library.UseCases.Loans.impl.BorrowBookUseCaseImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static main.java.com.library.Utils.Constants.*;

public class DataSeeder {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    private final BorrowBookUseCaseImpl borrowBookUseCaseImpl;

    public DataSeeder() {
        this.userRepository = new UserRepositoryImpl();
        this.bookRepository = new BookRepositoryImpl();
        this.loanRepository = new LoanRepositoryImpl();

        this.borrowBookUseCaseImpl = new BorrowBookUseCaseImpl(loanRepository, userRepository, bookRepository);
    }

    public void seed() throws IOException {
        recreateFiles();

        String user1Id = seedUser("John Doe", "johndoe", UserLevel.STANDARD);
        String user2Id = seedUser("Jane Smith", "janesmith", UserLevel.ADMIN);
        String user3Id = seedUser("Lucas Moraes", "lucmoraes", UserLevel.ADMIN);

        String book1Id = seedBook("Effective Java", "A comprehensive guide to best practices in Java.", 30);
        String book2Id = seedBook("Clean Code", "A handbook of agile software craftsmanship.", 45);

        seedLoan(user1Id, book1Id);
        seedLoan(user2Id, book2Id);
        seedLoan(user3Id, book1Id);
        seedLoan(user3Id, book2Id);
    }

    private void recreateFiles() throws IOException {
        Path booksPath = Paths.get(BOOKS_FILE_PATH);
        Files.deleteIfExists(booksPath);
        Files.createFile(booksPath);

        Path usersPath = Paths.get(LOANS_FILE_PATH);
        Files.deleteIfExists(usersPath);
        Files.createFile(usersPath);

        Path loansPath = Paths.get(USERS_FILE_PATH);
        Files.deleteIfExists(loansPath);
        Files.createFile(loansPath);
    }

    private String seedUser(String name, String username, UserLevel level) {
        User user = new User();
        user.setName(name);
        user.setUsername(username);
        user.setLevel(level);
        userRepository.save(user);
        return user.getId();
    }

    private String seedBook(String title, String description, int loanDuration) {
        Book book = new Book(title, description, loanDuration);
        bookRepository.addBook(book);
        return book.getId();
    }

    private void seedLoan(String userId, String bookId) {
        borrowBookUseCaseImpl.execute(userId, bookId);
    }
}
