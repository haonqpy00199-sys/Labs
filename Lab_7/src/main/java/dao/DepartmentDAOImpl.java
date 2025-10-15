package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Department;
import utils.Jdbc;

public class DepartmentDAOImpl implements DepartmentDAO {

    @Override
    public List<Department> findAll() {
        String sql = "SELECT * FROM Departments";
        try {
            List<Department> entities = new ArrayList<>();
            Object[] values = {};
            ResultSet rs = Jdbc.executeQuery(sql, values);
            while (rs.next()) {
                Department d = new Department();
                d.setId(rs.getString("Id"));
                d.setName(rs.getString("Name"));
                d.setDescription(rs.getString("Description"));
                entities.add(d);
            }
            // close ResultSet and its connection
            try { rs.getStatement().getConnection().close(); } catch (Exception ex) {}
            return entities;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Department findById(String id) {
        String sql = "SELECT * FROM Departments WHERE Id=?";
        try {
            Object[] values = { id };
            ResultSet rs = Jdbc.executeQuery(sql, values);
            if (rs.next()) {
                Department d = new Department();
                d.setId(rs.getString("Id"));
                d.setName(rs.getString("Name"));
                d.setDescription(rs.getString("Description"));
                try { rs.getStatement().getConnection().close(); } catch (Exception ex) {}
                return d;
            } else {
                try { rs.getStatement().getConnection().close(); } catch (Exception ex) {}
                throw new RuntimeException("Not found");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create(Department entity) {
        String sql = "INSERT INTO Departments(Id, Name, Description) VALUES(?, ?, ?)";
        try {
            Object[] values = {
                entity.getId(),
                entity.getName(),
                entity.getDescription()
            };
            Jdbc.executeUpdate(sql, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Department entity) {
        String sql = "UPDATE Departments SET Name=?, Description=? WHERE Id=?";
        try {
            Object[] values = {
                entity.getName(),
                entity.getDescription(),
                entity.getId()
            };
            Jdbc.executeUpdate(sql, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        String sql = "DELETE FROM Departments WHERE Id=?";
        try {
            Object[] values = { id };
            Jdbc.executeUpdate(sql, values);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}