package com.company.service;


import com.company.model.User;

import java.sql.*;
import java.util.List;
import java.util.Vector;

public class LibrarianService {

    public static List<Librarian> loadAllLibrarians() throws SQLException {
        List<Librarian> librariansList = new Vector<>();
        String selectAllLibrariansQuery = "SELECT * FROM librarian";
        try (Connection conn = DBService.open();
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(selectAllLibrariansQuery)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");

                Librarian librarian = new Librarian(id, login, password);
                librariansList.add(librarian);
            }
        }
        return librariansList;
    }

    public static boolean validateLibrarianLoginData(String login, String password) throws SQLException {
        String selectLibrarianByLoginAndPassword = "SELECT * FROM librarian WHERE login = ? AND password = ?";
        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(selectLibrarianByLoginAndPassword);) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }


    public static int insertLibrarian(Librarian librarian) throws SQLException {
        String addLibrarianQuery = "INSERT INTO librarian (login, password) VALUES(?,?)";
        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(addLibrarianQuery)) {
            ps.setString(1, librarian.getLogin());
            ps.setString(2, librarian.getPassword());
            return ps.executeUpdate();
        }
    }

    public static int updateLibrarian(Librarian librarian) throws SQLException {
        String updateLibrarianQuery = "UPDATE librarian set login = ?, " +
                "password = ? " +
                "where id = ?;";


        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(updateLibrarianQuery)) {
            ps.setString(1, librarian.getLogin());
            ps.setString(2, librarian.getPassword());
            ps.setInt(3, librarian.getId());

            return ps.executeUpdate();
        }
    }

    public static int deleteLibrarian(int id) throws SQLException {
        String deleteLibrarianByIdQuery = "DELETE FROM librarian WHERE id = ?";

        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(deleteLibrarianByIdQuery);) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }


}
