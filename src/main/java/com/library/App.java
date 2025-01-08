package main.java.com.library;

import main.java.com.library.UI.LibraryChatBotApp;
import main.java.com.library.Utils.DataSeeder;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        try {
            // Executa o seed de dados iniciais
            System.out.println("Executando seed de dados iniciais...");
            DataSeeder seeder = new DataSeeder();
            seeder.seed();
            System.out.println("Seed concluído com sucesso!");

            // Inicia o chatbot da biblioteca
            LibraryChatBotApp chatBotApp = new LibraryChatBotApp();
            chatBotApp.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
