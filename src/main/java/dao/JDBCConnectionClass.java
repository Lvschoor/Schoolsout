package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// created this class to enable ResetDatabase service to drop tables

public class JDBCConnectionClass {

    private JDBCConnectionClass() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://moktok.intecbrussel.org:33062/luc", "luc", "luc123");
    }
}
