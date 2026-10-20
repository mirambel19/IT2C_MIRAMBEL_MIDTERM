package com.library.dao;

import com.library.model.ScenarioEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public ScenarioDao() {
        createTable();
    }

    private void createTable() {

        String sql = """
                CREATE TABLE IF NOT EXISTS books (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    author TEXT NOT NULL,
                    isbn TEXT NOT NULL,
                    status TEXT NOT NULL
                )
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean addBook(ScenarioEntity book) {

        String sql = """
                INSERT INTO books (title, author, isbn, status)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getIsbn());
            statement.setString(4, book.getStatus());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<ScenarioEntity> getAllBooks() {

        List<ScenarioEntity> books = new ArrayList<>();

        String sql = """
                SELECT * FROM books ORDER BY id
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                ScenarioEntity book = new ScenarioEntity(
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn"),
                        resultSet.getString("status")
                );

                book.setId(resultSet.getInt("id"));

                books.add(book);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }
}