package com.library.Utils;

import com.library.Entities.Book;
import com.library.Entities.Loan;
import com.library.Entities.User;

public class ConvertToCsv {
    public static String convertBookToCsvLine(Book book) {
        return book.getId() + "," + book.getTitle() + "," + book.getDescription();
    }

    public static String convertLoanToCsvLine(Loan loan) {
        return String.join(",",
                loan.getId(),
                loan.getUserId(),
                loan.getBookId(),
                String.valueOf(loan.isActive()),
                loan.getStartDate().toString(),
                loan.getEndDate() != null ? loan.getEndDate().toString() : "");
    }

    public static String convertUserToCsvLine(User user) {
        return String.join(",",
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getLevel().toString());
    }
}
