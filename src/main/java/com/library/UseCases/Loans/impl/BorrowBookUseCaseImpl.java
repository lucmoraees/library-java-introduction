package main.java.com.library.UseCases.Loans.impl;

import main.java.com.library.Entities.Book;
import main.java.com.library.Entities.User;
import main.java.com.library.Repositories.BookRepository;
import main.java.com.library.Repositories.LoanRepository;
import main.java.com.library.Repositories.UserRepository;
import main.java.com.library.UseCases.Loans.BorrowBookUseCase;

import java.util.Optional;

public class BorrowBookUseCaseImpl implements BorrowBookUseCase {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public BorrowBookUseCaseImpl(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void execute(String userId, String bookId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo ou vazio.");
        }
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("O ID do livro não pode ser nulo ou vazio.");
        }

        Optional<User> user = userRepository.getUserById(userId);

        System.out.println(userId);

        if (user.isEmpty()) {
            throw new IllegalArgumentException("ID do usuário inválido.");
        }

        Optional<Book> book = bookRepository.getBookById(bookId);

        if (book.isEmpty()) {
            throw new IllegalArgumentException("ID do livro inválido.");
        }

        int loanDuration = book.get().getLoanDuration();

        loanRepository.borrowBook(userId, bookId, loanDuration);
    }
}
