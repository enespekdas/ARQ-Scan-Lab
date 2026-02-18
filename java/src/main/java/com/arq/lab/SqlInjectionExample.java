package com.arq.lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class SqlInjectionExample {
    public static String findUserByName(String username) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/arq";
        String sql = "SELECT id, username FROM users WHERE username = '" + username + "'"; // SQLi
        try (Connection c = DriverManager.getConnection(url, "arq", "SuperSecretPassword123!");
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getString("id") + ":" + rs.getString("username");
            }
            return null;
        }
    }
}
