package com.library.Utils;

import java.io.File;

public class Constants {
    public static final int MAX_LOAN_DAYS = 60;

    private static final String DATA_DIR_PATH = System.getProperty("user.dir") + File.separator + "DATA";

    public static final String BOOKS_FILE_PATH = DATA_DIR_PATH + File.separator + "books.csv";
    public static final String USERS_FILE_PATH = DATA_DIR_PATH + File.separator + "users.csv";
    public static final String LOANS_FILE_PATH = DATA_DIR_PATH + File.separator + "loans.csv";
}
