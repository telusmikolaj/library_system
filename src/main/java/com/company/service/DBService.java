package com.company.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private static final String url = "jdbc:sqlite:/Users/mikolajtelus/Documents/Java/CRUD2-1-1-1-2/src/main/java/com/company/service/library.db";

    public static Connection open() {
        try {
            Connection conn = DriverManager.getConnection(url);
            return conn;
        } catch (SQLException e) {
            System.out.println("Coludn't connect to database" + e.getMessage());
            return null;
        }
    }

}
