package main.java.com.library.Repositories.impl;

import main.java.com.library.Entities.Book;
import main.java.com.library.Repositories.BookRepository;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static main.java.com.library.Utils.Constants.BOOKS_FILE_PATH;
import static main.java.com.library.Utils.ConvertToCsv.convertBookToCsvLine;

public class BookRepositoryImpl implements BookRepository {
    public BookRepositoryImpl()  {
        Path path = Paths.get(BOOKS_FILE_PATH);
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Book> findById(int bookId) {
        Path path = Paths.get(BOOKS_FILE_PATH);

        try {
            if (Files.notExists(path)) {
                throw new RuntimeException("Arquivo não encontrado.");
            }

            return Files.readAllLines(path).stream()
                    .map(this::convertCsvLineToBook)
                    .filter(book -> book.getId().equals(bookId))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty(); // Retorna vazio se ocorrer um erro
    }

    @Override
    public void addBook(Book book) {
        Path path = Paths.get(BOOKS_FILE_PATH);

        try {
            String content = convertBookToCsvLine(book) + System.lineSeparator();
            Files.write(path, content.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(String bookId) {
        Path path = Paths.get(BOOKS_FILE_PATH);

        try {
            if (Files.notExists(path)) {
                throw new RuntimeException("Arquivo não encontrado.");
            }

            List<String> lines = Files.readAllLines(path);

            List<String> updatedLines = lines.stream()
                    .filter(line -> !line.startsWith(String.valueOf(bookId) + ","))
                    .collect(Collectors.toList());

            Files.write(path, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> getAvailableBooks() {
        Path path = Paths.get(BOOKS_FILE_PATH);
        List<Book> books = new ArrayList<>();

        try {
            if (Files.notExists(path)) {
                throw new RuntimeException("Arquivo não encontrado.");
            }

            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                Book book = convertCsvLineToBook(line);
                books.add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("Erro ao processar o arquivo CSV: " + e.getMessage());
        }

        return books;
    }

    @Override
    public void updateLoanDuration(String bookId, int days) {
        Path path = Paths.get(BOOKS_FILE_PATH);

        try {
            if (Files.notExists(path)) {
                throw new RuntimeException("Arquivo não encontrado.");
            }

            List<String> lines = Files.readAllLines(path);

            List<String> updatedLines = lines.stream()
                    .map(line -> {
                        String[] parts = line.split(",", 4);
                        if (parts[0].equals(bookId)) {
                            parts[3] = String.valueOf(days);
                            return String.join(",", parts);
                        }
                        return line;
                    })
                    .collect(Collectors.toList());

            Files.write(path, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.err.println("Erro ao atualizar a duração do empréstimo: " + e.getMessage());
        }
    }

    @Override
    public Optional<Book> getBookById(String bookId) {
        return getAvailableBooks().stream()
                .filter(user -> user.getId().equals(bookId))
                .findFirst();
    }

    private Book convertCsvLineToBook(String line) {
        String[] parts = line.split(",", 4);
        String id = parts[0];
        String title = parts[1];
        String description = parts[2];
        int loanDuration = Integer.parseInt(parts[3]);
        Book book = new Book(id, title, description, loanDuration);

        return book;
    }
}
