package com.company.service;

import com.company.MainProgram;
import com.company.model.Book;
import com.company.model.User;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {

    public static List<Book> loadAllBooks() throws SQLException {
        List<Book> booksList = new ArrayList<>();
        String selectAllBooksQuery = "SELECT * FROM book";

        try (Connection conn = DBService.open();
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(selectAllBooksQuery)) {
            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String publisher = resultSet.getString("publisher");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                String pages = resultSet.getString("pages");
                int availbility = resultSet.getInt("availbility");
                int numberOfBorrows = resultSet.getInt("borrows_number");

                Book book = new Book(bookId, title, author, genre, pages, publisher, availbility, numberOfBorrows);
                booksList.add(book);
            }
        }

        return booksList;
    }

    public static int insertBook(Book book) throws SQLException {
        String addBookQuery = "INSERT INTO book (title, publisher, author, availbility, genre, pages) VALUES (?,?,?,?,?,?);";
        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(addBookQuery);) {


            ps.setString(1, book.getTitle());
            ps.setString(2, book.getPublisher());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getAvailbility());
            ps.setString(5, book.getGenre());
            ps.setString(6, book.getPages());

            return ps.executeUpdate();
        }
    }

    public static int deleteBook(int id) throws SQLException {
        String deleteBookByIdQuery = "DELETE FROM book WHERE id = ?";

        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(deleteBookByIdQuery);) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public static boolean isBookExsited(int id) throws SQLException {
        String selectBookByIdQuery = "SELECT id FROM book where id = ?";
        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(selectBookByIdQuery);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public static boolean isBookAvailable(int id) throws SQLException {
        String selectBookByIdAndAvailbilityQuery = "SELECT * FROM book where id = ? AND availbility = ?";

        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(selectBookByIdAndAvailbilityQuery);) {
            ps.setInt(1, id);
            ps.setInt(2, 1);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public static void updateBook(Book book) throws SQLException {

        String updateBookQuery = "update book set title =?, author=?, pages=?, publisher=? where id=?";

        try (Connection conn = DBService.open();
             PreparedStatement stmt = conn.prepareStatement(updateBookQuery)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPages());
            stmt.setString(4, book.getPublisher());
            stmt.setInt(5, book.getId());

            stmt.executeUpdate();
            System.out.println("Database updated successfully ");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static List<Book> searchBookByTitleKeyWord(String titleKeyWord) throws SQLException {
        List<Book> booksList = new ArrayList<>();

        String searchBookByKeywordQuery = "SELECT * FROM book WHERE title LIKE ?";
        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(searchBookByKeywordQuery);) {

            ps.setString(1, "%" + titleKeyWord + "%");

            System.out.println(ps.toString());
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String publisher = resultSet.getString("publisher");
                    String author = resultSet.getString("author");
                    String genre = resultSet.getString("genre");
                    String pages = resultSet.getString("pages");
                    int availbility = resultSet.getInt("availbility");
                    int numberOfBorrows = resultSet.getInt("borrows_number");

                    Book book = new Book(bookId, title, author, genre, pages, publisher, availbility, numberOfBorrows);
                    booksList.add(book);
                }
            }
        }

        return booksList;
    }

    public static List<Book> searchBookByAuthor(String authorKeyWord) throws SQLException {
        List<Book> booksList = new ArrayList<>();

        String searchBookByKeywordQuery = "SELECT * FROM book WHERE author LIKE ?";
        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(searchBookByKeywordQuery);) {

            ps.setString(1, "%" + authorKeyWord + "%");

            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    int bookId = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String publisher = resultSet.getString("publisher");
                    String author = resultSet.getString("author");
                    String genre = resultSet.getString("genre");
                    String pages = resultSet.getString("pages");
                    int availbility = resultSet.getInt("availbility");
                    int numberOfBorrows = resultSet.getInt("borrows_number");

                    Book book = new Book(bookId, title, author, genre, pages, publisher, availbility, numberOfBorrows);
                    booksList.add(book);
                }
            }
        }

        return booksList;
    }

    public static Book findBookById(int id) throws SQLException {

        Book book = null;
        String findBookByIdQuery = "SELECT * FROM book WHERE id= ?";
        try (Connection c = DBService.open();
             PreparedStatement ps = c.prepareStatement(findBookByIdQuery)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    book = new Book(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("genre"),
                            rs.getString("pages"),
                            rs.getString("publisher"),
                            rs.getInt("availbility"),
                            rs.getInt("borrows_number"));
                }

            } catch (SQLException e) {
                System.out.println("Cannot find a book");
            }

        }

        return book;

    }

    public static int setAvailbility(Book book) throws SQLException {
        String setAvailbilityQuery = "UPDATE book set availbility=? where id=?";

        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(setAvailbilityQuery)) {

            ps.setInt(1, book.getAvailbility());
            ps.setInt(2, book.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Cannot set availbility");
            return 1;
        }
    }

    public static void setNumOfBorrows(Book book) throws SQLException {
        String increaseNumOfBorrowsQuery = "Update book set borrows_number =? WHERE id=? ";

        try (Connection conn = DBService.open();
             PreparedStatement stmt = conn.prepareStatement(increaseNumOfBorrowsQuery)) {

            stmt.setInt(1, book.getNumOfBorrows());
            stmt.setInt(2, book.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Number of borrows incremented !");
        }

    }

    public static List<Book> getMostReadedBooks() throws SQLException {

        List<Book> booksList = new ArrayList<>();
        String selectMostReadedBooksQuery = "SELECT * FROM book ORDER BY borrows_number  DESC LIMIT 3";

        try (Connection conn = DBService.open();
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(selectMostReadedBooksQuery)) {
            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");

                String title = resultSet.getString("title");
                String publisher = resultSet.getString("publisher");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                String pages = resultSet.getString("pages");
                int availbility = resultSet.getInt("availbility");
                int numberOfBorrows = resultSet.getInt("borrows_number");

                Book book = new Book(bookId, title, author, genre, pages, publisher, availbility, numberOfBorrows);
                booksList.add(book);
            }
        }

        return booksList;

    }

    public static List<Book> loadAllByAvailbility(int availbility) throws SQLException {
        List<Book> booksList = new ArrayList<>();
        String selectAllBooksByAvailbilityQuery = "SELECT * FROM book WHERE availbility = ?";

        try (Connection c = DBService.open();
             PreparedStatement ps = c.prepareStatement(selectAllBooksByAvailbilityQuery)) {
            ps.setInt(1, availbility);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Book book = new Book(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("genre"),
                            rs.getString("pages"),
                            rs.getString("publisher"),
                            rs.getInt("availbility"),
                            rs.getInt("borrows_number"));

                    booksList.add(book);
                }


            } catch (SQLException e) {
                System.out.println("Cannot find a book");
            }

        }

        return booksList;
    }

    public static Book searchBookByTitle(String title) throws SQLException {
        Book book = null;
        String findBookByTitleQuery = "SELECT * FROM book WHERE title= ?";
        try (Connection c = DBService.open();
             PreparedStatement ps = c.prepareStatement(findBookByTitleQuery)) {
            ps.setString(1, title);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    book = new Book(rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("genre"),
                            rs.getString("pages"),
                            rs.getString("publisher"),
                            rs.getInt("availbility"),
                            rs.getInt("borrows_number"));
                }

            } catch (SQLException e) {
                System.out.println("Cannot find a book");
            }

        }

        return book;


    }

    public static List<Book> getMostReadedBooksForChart() throws SQLException {

        List<Book> booksList = new ArrayList<>();
        String selectMostReadedBooksQuery = "SELECT * FROM book ORDER BY borrows_number  DESC LIMIT 6";

        try (Connection conn = DBService.open();
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(selectMostReadedBooksQuery)) {
            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");

                String title = resultSet.getString("title");
                String publisher = resultSet.getString("publisher");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                String pages = resultSet.getString("pages");
                int availbility = resultSet.getInt("availbility");
                int numberOfBorrows = resultSet.getInt("borrows_number");

                Book book = new Book(bookId, title, author, genre, pages, publisher, availbility, numberOfBorrows);
                booksList.add(book);
            }
        }

        return booksList;

    }

    public static Map<String, Integer> getMostReadedAuthorsForChart() throws SQLException {

        String selectMostReadedAuthor = "SELECT *, SUM(borrows_number) AS total_borrows FROM book GROUP BY author ORDER BY SUM(borrows_number) DESC LIMIT 3;";
        Map<String, Integer> topAuthorMap = new HashMap<>();

        try (Connection conn = DBService.open();
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(selectMostReadedAuthor)) {
            while (resultSet.next()) {
                String author = resultSet.getString("author");
                int totalNumOfBorrows = resultSet.getInt("total_borrows");

                System.out.println(author);
                topAuthorMap.put(author, totalNumOfBorrows);
            }
        }

        return topAuthorMap;

    }
}
