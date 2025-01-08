package main.java.com.library.UseCases.Books.impl;

import main.java.com.library.Repositories.BookRepository;
import main.java.com.library.UseCases.Books.UpdateLoanDurationUseCase;

public class UpdateLoanDurationUseCaseImpl implements UpdateLoanDurationUseCase {

    private final BookRepository bookRepository;
    private static final int MAX_LOAN_DAYS = 60;

    public UpdateLoanDurationUseCaseImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void execute(String bookId, int days) {
        if (bookId == null || bookId.isEmpty()) {
            throw new RuntimeException("O ID do livro não pode ser nulo ou vazio.");
        }

        if (days <= 0 || days > MAX_LOAN_DAYS) {
            throw new RuntimeException("O tempo de empréstimo deve estar entre 1 e " + MAX_LOAN_DAYS + " dias.");
        }

        bookRepository.updateLoanDuration(bookId, days);
    }
}
