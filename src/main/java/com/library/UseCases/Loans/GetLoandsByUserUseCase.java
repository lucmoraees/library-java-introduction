package main.java.com.library.UseCases.Loans;

import main.java.com.library.Entities.Loan;

import java.util.List;

public interface GetLoandsByUserUseCase {
    List<Loan> execute(String userId);
}