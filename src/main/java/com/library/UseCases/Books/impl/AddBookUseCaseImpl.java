package main.java.com.library.UseCases.Books.impl;

import main.java.com.library.Entities.Book;
import main.java.com.library.Repositories.BookRepository;
import main.java.com.library.UseCases.Books.AddBookUseCase;

public class AddBookUseCaseImpl implements AddBookUseCase {

    private final BookRepository bookRepository;

    public AddBookUseCaseImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void execute(Book book) {
        if (book == null || book.getTitle() == null || book.getDescription() == null) {
            throw new IllegalArgumentException("Os dados do livro não podem ser nulos.");
        }
        bookRepository.addBook(book);
        System.out.println("Livro '" + book.getTitle() + "' cadastrado com sucesso.");
    }
}
