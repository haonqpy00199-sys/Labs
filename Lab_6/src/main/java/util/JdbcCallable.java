package util;

import java.sql.*;

public class JdbcCallable {
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String dburl = "jdbc:sqlserver://localhost:1433;databaseName=HRM;encrypt=false;trustServerCertificate=true";
	static String username = "sa";
	static String password = "123456";
	static {
		try { // nạp driver
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
	public static int executeUpdate(String sql, Object... values) throws SQLException {
		Connection connection = getConnection();
		CallableStatement statement = connection.prepareCall(sql);
		for (int i = 0; i < values.length; i++) {
			statement.setObject(i + 1, values[i]);
		}
		return statement.executeUpdate();
	}

	/** Truy vấn dữ liệu */
	public static ResultSet executeQuery(String sql, Object... values) throws SQLException {
		Connection connection = getConnection();
		CallableStatement statement = connection.prepareCall(sql);
		for (int i = 0; i < values.length; i++) {
			statement.setObject(i + 1, values[i]);
		}
		return statement.executeQuery();
	}
}