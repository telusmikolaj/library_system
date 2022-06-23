package com.company.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminService {

    public static boolean validateAdminLoginData(String login, String password) throws SQLException {
        String selectAdminByLoginAndPassword = "SELECT * FROM admin WHERE login = ? AND password = ?";
        try (Connection conn = DBService.open();
             PreparedStatement ps = conn.prepareStatement(selectAdminByLoginAndPassword);) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
