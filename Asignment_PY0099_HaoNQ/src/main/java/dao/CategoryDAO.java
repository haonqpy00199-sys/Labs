package dao;

import model.Category;
import util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public List<Category> findAll() throws Exception {
        String sql = "SELECT Id,Name FROM Categories ORDER BY Name";
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Category> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    public Category findById(int id) throws Exception {
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT Id,Name FROM Categories WHERE Id=?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public void create(String name) throws Exception {
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO Categories(Name) VALUES (?)")) {
            ps.setString(1, name); ps.executeUpdate();
        }
    }
    public void update(int id, String name) throws Exception {
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement("UPDATE Categories SET Name=? WHERE Id=?")) {
            ps.setString(1, name); ps.setInt(2, id); ps.executeUpdate();
        }
    }
    public void delete(int id) throws Exception {
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM Categories WHERE Id=?")) {
            ps.setInt(1, id); ps.executeUpdate();
        }
    }


    private Category map(ResultSet rs) throws Exception {
        Category c = new Category();
        c.setId(rs.getInt("Id"));
        c.setName(rs.getString("Name"));
        return c;
    }
    
    

}
