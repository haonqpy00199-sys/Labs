package utils;

import java.sql.*;

public class Jdbc {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    // Use your server host port and database name
    static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=Lab07;encrypt=false;trustServerCertificate=true";
    static String username = "sa";
    static String password = "123456";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dburl, username, password);
    }

    public static int executeUpdate(String sql, Object... values) throws SQLException {
        PreparedStatement stmt = createPreStmt(sql, values);
        try {
            return stmt.executeUpdate();
        } finally {
            // Do not close stmt or connection here if you need to use generated keys
            // For this lab we close resources to avoid leaks
            try { stmt.getConnection().close(); } catch (Exception ex) {}
        }
    }

    public static ResultSet executeQuery(String sql, Object... values) throws SQLException {
        PreparedStatement stmt = createPreStmt(sql, values);
        // do not close connection here because resultset depends on it
        return stmt.executeQuery();
    }

    private static PreparedStatement createPreStmt(String sql, Object... values) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement stmt;
        String trimmed = sql.trim();
        if (trimmed.startsWith("{") && trimmed.endsWith("}")) {
            stmt = connection.prepareCall(sql);
        } else {
            stmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < values.length; i++) {
            stmt.setObject(i + 1, values[i]);
        }
        return stmt;
    }
}