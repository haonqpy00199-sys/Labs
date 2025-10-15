package test;
import java.sql.*;
import util.JdbcStatement;

public class TestStatement {
	public static void main(String[] args) {
		// ví dụ về SELECT
		try {
			String sql = "SELECT * FROM Departments";
			ResultSet resultSet = JdbcStatement.executeQuery(sql);
			while (resultSet.next()) {
				// in ra dữ liệu trả về theo thứ tự của cột
				System.out.println("in ra dữ liệu trả về theo thứ tự của cột");
				System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2) + " - " + resultSet.getString(3));
				// in ra dữ liệu trả về theo tên của cột
				System.out.println("in ra dữ liệu trả về theo tên của cột");
				System.out.println(
						resultSet.getString("Id") + " - " + resultSet.getString("Name") + " - " + resultSet.getString("Description"));
				System.out.println("-----------------------------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// ví dụ về INSERT
		try {
			String insert = "INSERT INTO Departments VALUES('D07', N'HR', N'tuyen dung')";
			int rows = JdbcStatement.executeUpdate(insert);
			System.out.println(rows + " row(s) inserted.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}