package main.java.com.library.UseCases.Loans.impl;

import main.java.com.library.Repositories.LoanRepository;
import main.java.com.library.UseCases.Loans.BorrowBookUseCase;

public class BorrowBookUseCaseImpl implements BorrowBookUseCase {
    private final LoanRepository loanRepository;

    public BorrowBookUseCaseImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public void execute(String userId, String bookId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo ou vazio.");
        }
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("O ID do livro não pode ser nulo ou vazio.");
        }
        loanRepository.borrowBook(userId, bookId);
        System.out.println("Empréstimo do livro com ID '" + bookId + "' realizado com sucesso para o usuário '" + userId + "'.");
    }
}
