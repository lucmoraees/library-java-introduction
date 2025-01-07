package main.java.com.library.UseCases.Books;

public interface UpdateLoanDurationUseCase {
    void execute(String bookId, int days);
}
