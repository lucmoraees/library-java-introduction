package com.library.Repositories;

import com.library.Entities.Loan;
import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    void borrowBook(String userId, String bookId);
    void returnBook(String loanId);
    Optional<Loan> getLoanById(String loanId);
    List<Loan> getActiveLoansByUser(String userId);
}
