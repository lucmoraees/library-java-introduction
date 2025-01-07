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

import java.util.UUID;

public class DataSeeder {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public DataSeeder() {
        this.userRepository = new UserRepositoryImpl();
        this.bookRepository = new BookRepositoryImpl();
        this.loanRepository = new LoanRepositoryImpl();
    }

    public void seed() {
        String user1Id = seedUser("John Doe", "johndoe", UserLevel.STANDARD);
        String user2Id = seedUser("Jane Smith", "janesmith", UserLevel.ADMIN);

        String book1Id = seedBook("Effective Java", "A comprehensive guide to best practices in Java.", 30);
        String book2Id = seedBook("Clean Code", "A handbook of agile software craftsmanship.", 45);

        seedLoan(user1Id, book1Id);
        seedLoan(user2Id, book2Id);

        System.out.println("Seed concluído com sucesso!");
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
        String id = UUID.randomUUID().toString();
        Book book = new Book(id, title, description, loanDuration);
        bookRepository.addBook(book);
        return id;
    }

    private void seedLoan(String userId, String bookId) {
        loanRepository.borrowBook(userId, bookId);
    }

    public static void main(String[] args) {
        DataSeeder seeder = new DataSeeder();
        seeder.seed();
    }
}
