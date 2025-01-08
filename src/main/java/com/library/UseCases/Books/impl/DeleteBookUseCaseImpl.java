package main.java.com.library.UseCases.Books.impl;

import main.java.com.library.Repositories.BookRepository;
import main.java.com.library.UseCases.Books.DeleteBookUseCase;

public class DeleteBookUseCaseImpl implements DeleteBookUseCase {

    private final BookRepository bookRepository;

    public DeleteBookUseCaseImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void execute(String bookId) {
        if (bookId == null || bookId.isEmpty()) {
            throw new IllegalArgumentException("O ID do livro não pode ser nulo ou vazio.");
        }
        bookRepository.deleteBook(bookId);
    }
}
