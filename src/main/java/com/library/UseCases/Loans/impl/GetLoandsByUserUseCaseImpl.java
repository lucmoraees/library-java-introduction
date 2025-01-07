package main.java.com.library.UseCases.Loans.impl;

import main.java.com.library.Entities.Loan;
import main.java.com.library.Repositories.LoanRepository;
import main.java.com.library.UseCases.Loans.GetLoandsByUserUseCase;

import java.util.ArrayList;
import java.util.List;

public class GetLoandsByUserUseCaseImpl implements GetLoandsByUserUseCase {

    private final LoanRepository loanRepository;

    public GetLoandsByUserUseCaseImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<Loan> execute(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo ou vazio.");
        }

        List<Loan> loans = loanRepository.getActiveLoansByUser(userId);
        if (loans.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo encontrado para o usuário com ID '" + userId + "'.");
            return new ArrayList<>();
        }

        System.out.println("Empréstimos ativos encontrados para o usuário com ID '" + userId + "': " + loans.size());
        return loans;
    }
}
