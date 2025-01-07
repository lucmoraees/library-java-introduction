package main.java.com.library.Repositories.impl;

import main.java.com.library.Entities.User;
import main.java.com.library.Entities.UserLevel;
import main.java.com.library.Repositories.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static main.java.com.library.Utils.Constants.USERS_FILE_PATH;
import static main.java.com.library.Utils.ConvertToCsv.convertUserToCsvLine;

public class UserRepositoryImpl implements UserRepository {
    public UserRepositoryImpl() {
        Path path = Paths.get(USERS_FILE_PATH);
        try {
            if (Files.notExists(path)) {
                Files.createFile(path);
                String header = "Id,Name,Username,Level" + System.lineSeparator();
                Files.write(path, header.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        Path path = Paths.get(USERS_FILE_PATH);
        String userRecord = convertUserToCsvLine(user) + System.lineSeparator();

        try {
            Files.write(path, userRecord.getBytes(), StandardOpenOption.APPEND);
            System.out.println("Usuário salvo com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        Path path = Paths.get(USERS_FILE_PATH);

        try {
            if (Files.notExists(path)) {
                System.out.println("Arquivo de usuários não encontrado.");
                return;
            }

            List<String> lines = Files.readAllLines(path);

            List<String> updatedLines = lines.stream()
                    .filter(line -> !line.startsWith(user.getId() + ","))
                    .collect(Collectors.toList());

            Files.write(path, updatedLines, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Usuário deletado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        Path path = Paths.get(USERS_FILE_PATH);
        List<User> users = new ArrayList<>();

        try {
            if (Files.notExists(path)) {
                return users;
            }

            users = Files.readAllLines(path).stream()
                    .skip(1)
                    .map(this::convertCsvLineToUser)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return getUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return getUsers().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(userName))
                .findFirst();
    }

    @Override
    public boolean isAdmin(String userId) {
        return getUserById(userId)
                .map(user -> user.getLevel() == UserLevel.ADMIN)
                .orElse(false);
    }

    private User convertCsvLineToUser(String line) {
        String[] parts = line.split(",", 4);
        User user = new User();
        user.setName(parts[1]);
        user.setUsername(parts[2]);
        user.setLevel(UserLevel.valueOf(parts[3]));
        return user;
    }
}
