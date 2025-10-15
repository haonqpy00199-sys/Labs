package test;

import java.sql.ResultSet;

import util.JdbcCallable;

public class TestCallable {
	public static void main(String[] args) {

		// CALL spSelectAll() , ví dụ về selectall

		try {
			String sql = "{CALL spSelectAll()}";
			ResultSet resultSet = JdbcCallable.executeQuery(sql);
			while (resultSet.next()) {
				System.out.println(resultSet.getString("Id") + " | " + resultSet.getString("Name") + " | "
						+ resultSet.getString("Description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// CALL spSelectById(?) ví dụ về call select by id
		try {
			String sql = "{CALL spSelectById(?)}";
			Object[] values = { "D02" };
			ResultSet resultSet = JdbcCallable.executeQuery(sql, (Object[]) values);
			while (resultSet.next()) {
				String value = resultSet.getString("Id") + " - " + resultSet.getString("Name") + " - "
						+ resultSet.getString("Description");
				System.out.println(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
// Những hàm dưới làm tới đâu mở ra tới đó
//        // CALL spInsert(?, ?, ?) ví dụ về insert
//
        try {
            String sql = "{CALL spInsert(?, ?, ?)}";
			Object[] values = { "D09", "Logistics", "Logistics and Transport" };
            int rows = JdbcCallable.executeUpdate(sql, values);
            System.out.println(rows + " row inserted");
        } catch (Exception e) {
            e.printStackTrace();
        }

		// CALL spUpdate(?, ?, ?) , ví dụ về update
		try {
			String sql = "{CALL spUpdate(?, ?, ?)}";
			Object[] values = { "D06", "Operations", "Operations and Delivery" };
			int rows = JdbcCallable.executeUpdate(sql, values);
			System.out.println(rows);
		} catch (Exception e) {
			e.printStackTrace();
		}


        // CALL spDeleteById(?)  → vidu5 về delete

        try {
            String sql = "{CALL spDeleteById(?)}";
            Object[] values = { "D09" };
            int rows = JdbcCallable.executeUpdate(sql, values);
            System.out.println(rows + " row deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}