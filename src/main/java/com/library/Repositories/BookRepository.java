package main.java.com.library.Repositories;

import main.java.com.library.Entities.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(int id);
    void addBook(Book book);
    void deleteBook(String bookId);
    List<Book> getAvailableBooks();
    void updateLoanDuration(String bookId, int days);
}
