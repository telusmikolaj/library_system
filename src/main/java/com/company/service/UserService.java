package com.company.service;

import com.company.model.Book;
import com.company.model.User;
import org.sqlite.core.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UserService {
    public static Vector<User> loadAllUsers() throws SQLException {
        Vector<User> usersList = new Vector<>();
        String selectAllUsersQuery = "SELECT * FROM user";
        try (Connection conn = DBService.open();
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(selectAllUsersQuery)) {

            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int numberOfBorrows = resultSet.getInt("borrows_number");

                User user = new User(userId, name, email, phone, numberOfBorrows);
                usersList.add(user);
            }
        }
        return usersList;
    }

    public static User findUserById(int id) throws SQLException {
        User user = null;
        String findUserByIdQuery = "SELECT * FROM user WHERE id ?";
        try (Connection c = DBService.open();
             PreparedStatement ps = c.prepareStatement(findUserByIdQuery); ) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if(rs.next()) {
                    user = new User(rs.getString("name"),
                                    rs.getString("email"),
                                    rs.getString("phone"));
                }

            }

        }

        return user;
    }

    public static int insertUser(User user) throws SQLException {
        String addUserQuery = "INSERT INTO USER (name, email, phone) VALUES(?,?,?)";
        try (Connection conn = DBService.open();
            PreparedStatement ps = conn.prepareStatement(addUserQuery)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3,user.getPhone());
            return ps.executeUpdate();
        }
    }

    public static int updateUser(User user) throws SQLException {
        String updateUserQuery = "UPDATE USER set name = ?, " +
                "email = ?, " +
                "phone = ? " +
                "where id = ?;";


        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(updateUserQuery)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setInt(4, user.getId());

            return ps.executeUpdate();
        }
    }

    public static boolean isUserExsited(int id) throws SQLException {
        String selectUserByIdQuery = "SELECT id FROM user where id = ?";
        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(selectUserByIdQuery);) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public static int deleteUser(int id) throws SQLException {
        String deleteUserByIdQuery = "DELETE FROM user WHERE id = ?";

        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(deleteUserByIdQuery);) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    public static void setNumOfBorrows(User user) throws SQLException {
        String increaseNumOfBorrowsQuery = "Update user set borrows_number =? WHERE id=? ";

        try (Connection conn = DBService.open();
             PreparedStatement stmt = conn.prepareStatement(increaseNumOfBorrowsQuery)) {

            stmt.setInt(1,user.getNumberOfBorrows());
            stmt.setInt(2,user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Number of borrows incremented !");
        }

    }

    public static List<User> getTopReader() throws SQLException {

        List<User> usersList = new ArrayList<>();
        String selectTopReaderQuery = "SELECT * FROM user ORDER BY borrows_number  DESC LIMIT 3";

        try (Connection conn = DBService.open();
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(selectTopReaderQuery)) {
            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int numberOfBorrows = resultSet.getInt("borrows_number");

                User user = new User(userId, name, email, phone, numberOfBorrows);
                usersList.add(user);
            }
        }

        return usersList;

    }



}
