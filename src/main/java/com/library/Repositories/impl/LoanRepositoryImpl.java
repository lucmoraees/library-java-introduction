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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void borrowBook(String userId, String bookId, int daysToReturn) {
        Loan loan = new Loan();
        loan.setUserId(userId);
        loan.setBookId(bookId);
        loan.setActive(true);
        loan.setStartDate(LocalDateTime.now());
        loan.setEndDate(LocalDateTime.now().plusDays(daysToReturn));
        String loanRecord = convertLoanToCsvLine(loan) + System.lineSeparator();

        try {
            Files.write(Paths.get(LOANS_FILE_PATH), loanRecord.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnBook(String loanId) {
        Path path = Paths.get(LOANS_FILE_PATH);

        try {
            if (Files.notExists(path)) {
                throw new RuntimeException("Arquivo de empréstimos não encontrado.");
            }

            List<String> lines = Files.readAllLines(path);

            List<String> updatedLines = lines.stream()
                    .map(line -> {
                        Loan loan = convertCsvLineToLoan(line);

                        if (!loan.getId().equals(loanId)) {
                            return line;
                        }

                        return "";
                    })
                    .collect(Collectors.toList());

            Files.write(path, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);

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

        String loanId = parts[0];
        String userId = parts[1];
        String bookId = parts[2];
        Boolean active = Boolean.parseBoolean(parts[3]);
        LocalDateTime startDate = LocalDateTime.parse(parts[4]);
        LocalDateTime endDate = LocalDateTime.parse(parts[5]);

        Loan loan = new Loan(loanId, userId, bookId, active, startDate, endDate);

        return loan;
    }
}