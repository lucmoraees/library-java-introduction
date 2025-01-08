package main.java.com.library.UseCases.Loans.impl;

import main.java.com.library.Entities.Loan;
import main.java.com.library.Repositories.LoanRepository;
import main.java.com.library.UseCases.Loans.GetLoansByUserUseCase;
import main.java.com.library.UseCases.Loans.GetLoansByUserUseCase;

import java.util.ArrayList;
import java.util.List;

public class GetLoansByUserUseCaseImpl implements GetLoansByUserUseCase {

    private final LoanRepository loanRepository;

    public GetLoansByUserUseCaseImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Loan> execute(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo ou vazio.");
        }

        List<Loan> loans = loanRepository.getActiveLoansByUser(userId);

        if (loans.isEmpty()) {
            throw new RuntimeException("Nenhum empréstimo ativo encontrado para o usuário com ID '" + userId + "'.");
        }

        return loans;
    }
}
