package main.java.com.library.UseCases.Books;

import main.java.com.library.Entities.Book;

import java.util.List;

public interface ListAvailableBooksUseCase {
    List<Book> execute();
}
