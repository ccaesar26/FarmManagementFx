package org.example.farmmanagementfx.data.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_farm", "postgres", "1q2w3e");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (connection == null) {
            System.out.println("Connection is null");
        } else {
            System.out.println("Connection is not null");
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();

            if (connection.isClosed()) {
                System.out.println("Connection is closed");
            } else {
                System.out.println("Connection is not closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
