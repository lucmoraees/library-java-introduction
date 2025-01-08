package main.java.com.library.UseCases.Books.impl;

import main.java.com.library.Entities.Book;
import main.java.com.library.Repositories.BookRepository;
import main.java.com.library.UseCases.Books.ListAvailableBooksUseCase;

import java.util.List;

public class ListAvailableBooksUseCaseImpl implements ListAvailableBooksUseCase {

    private final BookRepository bookRepository;

    public ListAvailableBooksUseCaseImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> execute() {
        List<Book> books = bookRepository.getAvailableBooks();
        if (books.isEmpty()) {
            throw new RuntimeException("Nenhum livro disponível no momento.");
        }
        return books;
    }
}
