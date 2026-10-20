package com.library.ui;

import com.library.dao.ScenarioDao;
import com.library.model.ScenarioEntity;

import java.util.List;
import java.util.Scanner;

public class ScenarioMenu {

    private final ScenarioDao dao;
    private final Scanner scanner = new Scanner(System.in);

    public ScenarioMenu(ScenarioDao dao) {
        this.dao = dao;
    }

    public void start() {

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("     LIBRARY BOOK CATALOG");
            System.out.println("==============================");
            System.out.println("[1] Add New Book");
            System.out.println("[2] View Book Catalog");
            System.out.println("[3] Exit System");
            System.out.println("==============================");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addBook();
                    break;

                case "2":
                    viewBooks();
                    break;

                case "3":
                    running = false;
                    System.out.println("Exiting system... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addBook() {

        System.out.println("\n--- ADD NEW BOOK ---");

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter Status (AVAILABLE / BORROWED): ");
        String status = scanner.nextLine().toUpperCase();

        if (title.isBlank()
                || author.isBlank()
                || isbn.isBlank()
                || !(status.equals("AVAILABLE")
                || status.equals("BORROWED"))) {

            System.out.println("Invalid input!");
            return;
        }

        ScenarioEntity book = new ScenarioEntity(
                title, author, isbn, status
        );

        if (dao.addBook(book)) {
            System.out.println(
                    "SUCCESS: Book cataloged into database!"
            );
        } else {
            System.out.println("Failed to add book.");
        }
    }

    private void viewBooks() {

        List<ScenarioEntity> books = dao.getAllBooks();

        System.out.println("\nLIBRARY BOOK CATALOG");

        System.out.printf(
                "%-5s %-25s %-20s %-18s %-12s%n",
                "ID", "TITLE", "AUTHOR", "ISBN", "STATUS"
        );

        System.out.println("-".repeat(85));

        for (ScenarioEntity book : books) {

            System.out.printf(
                    "%-5d %-25.25s %-20.20s %-18.18s %-12s%n",
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getStatus()
            );
        }

        System.out.println("-".repeat(85));
        System.out.println("Total Records Displayed: " + books.size());
    }
}