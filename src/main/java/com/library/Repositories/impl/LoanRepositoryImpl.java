package main.java.com.library.Repositories.impl;

import main.java.com.library.Entities.Loan;
import main.java.com.library.Repositories.LoanRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static main.java.com.library.Utils.Constants.LOANS_FILE_PATH;
import static main.java.com.library.Utils.ConvertToCsv.convertLoanToCsvLine;

public class LoanRepositoryImpl implements LoanRepository {
    public LoanRepositoryImpl() {
        Path path = Paths.get(LOANS_FILE_PATH);
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
                String header = "Id,UserId,BookId,Active,StartDate,EndDate" + System.lineSeparator();
                Files.write(path, header.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrowBook(String userId, String bookId) {
        Loan loan = new Loan(userId, bookId);
        String loanRecord = convertLoanToCsvLine(loan) + System.lineSeparator();

        try {
            Files.write(Paths.get(LOANS_FILE_PATH), loanRecord.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Empréstimo registrado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnBook(String loanId) {
        Path path = Paths.get(LOANS_FILE_PATH);

        try {
            if (Files.notExists(path)) {
                System.out.println("Arquivo de empréstimos não encontrado.");
                return;
            }

            List<String> lines = Files.readAllLines(path);

            List<String> updatedLines = lines.stream()
                    .map(line -> {
                        String[] parts = line.split(",", 6);
                        if (parts[0].equals(loanId) && Boolean.parseBoolean(parts[3])) {
                            parts[3] = "false";
                            parts[5] = LocalDateTime.now().toString();
                            return String.join(",", parts);
                        }
                        return line;
                    })
                    .collect(Collectors.toList());

            Files.write(path, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Livro devolvido com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Loan> getLoanById(String loanId) {
        Path path = Paths.get(LOANS_FILE_PATH);

        try {
            if (Files.notExists(path)) {
                return Optional.empty();
            }

            return Files.readAllLines(path).stream()
                    .skip(1) // Ignora o cabeçalho
                    .map(this::convertCsvLineToLoan)
                    .filter(loan -> loan.getId().equals(loanId))
                    .findFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Loan> getActiveLoansByUser(String userId) {
        Path path = Paths.get(LOANS_FILE_PATH);
        List<Loan> activeLoans = new ArrayList<>();

        try {
            if (Files.notExists(path)) {
                return activeLoans;
            }

            activeLoans = Files.readAllLines(path).stream()
                    .skip(1) // Ignora o cabeçalho
                    .map(this::convertCsvLineToLoan)
                    .filter(loan -> loan.getUserId().equals(userId) && loan.isActive())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return activeLoans;
    }

    private Loan convertCsvLineToLoan(String line) {
        String[] parts = line.split(",", 6);
        Loan loan = new Loan(parts[1], parts[2]);
        loan.setActive(Boolean.parseBoolean(parts[3]));
        loan.setEndDate(parts[5].isEmpty() ? null : LocalDateTime.parse(parts[5]));
        return loan;
    }
}