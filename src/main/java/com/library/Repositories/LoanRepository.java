package main.java.com.library.Repositories;

import main.java.com.library.Entities.Loan;
import java.util.List;
import java.util.Optional;

public interface LoanRepository {
    void borrowBook(String userId, String bookId, int daysToReturn);
    void returnBook(String loanId);
    Optional<Loan> getLoanById(String loanId);
    List<Loan> getActiveLoansByUser(String userId);
}
