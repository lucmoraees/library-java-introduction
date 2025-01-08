package main.java.com.library.UseCases.Loans.impl;

import main.java.com.library.Repositories.LoanRepository;
import main.java.com.library.UseCases.Loans.ReturnBookUseCase;

public class ReturnBookUseCaseImpl implements ReturnBookUseCase {

    private final LoanRepository loanRepository;

    public ReturnBookUseCaseImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public void execute(String loanId) {
        if (loanId == null || loanId.isEmpty()) {
            throw new IllegalArgumentException("O ID do empréstimo não pode ser nulo ou vazio.");
        }

        loanRepository.returnBook(loanId);
    }
}
