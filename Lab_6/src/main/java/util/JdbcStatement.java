package util;

import java.sql.*;

public class JdbcStatement {
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=HRM;encrypt=false;trustServerCertificate=true";
	static String username = "sa";
	static String password = "123456";
	static {
		try {
			// nạp driver
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/** Mở kết nối */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(dburl, username, password);
	}

	/** Thao tác dữ liệu */
	public static int executeUpdate(String sql) throws SQLException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		return statement.executeUpdate(sql);
	}

	/** Truy vấn dữ liệu */
	public static ResultSet executeQuery(String sql) throws SQLException {
		Connection connection = getConnection();
		Statement statement = connection.createStatement();
		return statement.executeQuery(sql);
	}
}
