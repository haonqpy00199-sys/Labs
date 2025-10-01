package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.User;
import util.DB;

public class UserDAO {

    /* ======================== CRUD & Auth ======================== */

    public User findById(int id) throws Exception {
        String sql = "SELECT * FROM Users WHERE Id=?";
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<User> findAll() throws Exception {
        String sql = "SELECT * FROM Users ORDER BY Id DESC";
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<User> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    /** Tạo user mới. Role: true=Admin, false=Reporter */
    public int create(User u) throws Exception {
        String sql = "INSERT INTO Users(Fullname, Email, [Password], Mobile, Birthday, Gender, Role, Activated) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, 1)";
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getFullname());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getMobile());

            // birthday: java.util.Date -> java.sql.Date
            if (u.getBirthday() != null) {
                ps.setDate(5, new java.sql.Date(u.getBirthday().getTime()));
            } else {
                ps.setNull(5, Types.DATE);
            }

            // gender: primitive boolean (nếu bạn dùng Boolean, đổi sang setObject với Types.BIT)
            ps.setBoolean(6, u.isGender());

            // role: BIT
            ps.setBoolean(7, u.isRole());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }

    /** Đăng nhập (Activated=1) */
    public User login(String email, String password) throws Exception {
        String sql = "SELECT * FROM Users WHERE Email=? AND [Password]=? AND Activated=1";
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    /** Cập nhật thông tin người dùng (bao gồm role & gender) */
    public void update(User u) throws Exception {
        String sql = """
            UPDATE Users
               SET [Password]=?, Fullname=?, Birthday=?, Gender=?, Mobile=?, Email=?, Role=?
             WHERE Id=?
            """;
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, u.getPassword());
            ps.setString(2, u.getFullname());

            if (u.getBirthday() != null) {
                ps.setDate(3, new java.sql.Date(u.getBirthday().getTime()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            // gender
            ps.setBoolean(4, u.isGender());
            // Nếu model của bạn là Boolean có thể null:
            // if (u.getGender() == null) ps.setNull(4, Types.BIT); else ps.setBoolean(4, u.getGender());

            ps.setString(5, u.getMobile());
            ps.setString(6, u.getEmail());

            // role
            ps.setBoolean(7, u.isRole());

            ps.setInt(8, u.getId());

            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM Users WHERE Id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public boolean existsEmail(String email) throws Exception {
        String sql = "SELECT 1 FROM Users WHERE Email=?";
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /* ======================== Helpers ======================== */

    /** Map 1 dòng ResultSet -> User (birthday java.util.Date, gender/role boolean) */
    private User map(ResultSet rs) throws Exception {
        User u = new User();

        u.setId(rs.getInt("Id"));
        u.setPassword(rs.getString("Password"));
        u.setFullname(rs.getString("Fullname"));

        java.sql.Date bd = rs.getDate("Birthday");
        if (bd != null) {
            u.setBirthday(new java.util.Date(bd.getTime())); // model dùng java.util.Date
        } else {
            u.setBirthday(null);
        }

        // gender & role: BIT -> boolean
        u.setGender(rs.getBoolean("Gender"));
        // Nếu Gender trong DB có thể NULL mà bạn muốn giữ null trong model:
        // Object g = rs.getObject("Gender");
        // if (u.getClass().getMethod("setGender", Boolean.class) != null) {
        //     u.setGender(g == null ? null : rs.getBoolean("Gender"));
        // } else {
        //     u.setGender(g != null && rs.getBoolean("Gender"));
        // }

        u.setMobile(rs.getString("Mobile"));
        u.setEmail(rs.getString("Email"));
        u.setRole(rs.getBoolean("Role")); // true=Admin, false=Reporter
        u.setActivated(rs.getBoolean("Activated"));

        return u;
    }
    
    public int countAll() throws Exception {
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM Users");
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
    public void setActivated(int id, boolean on) throws Exception {
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement("UPDATE Users SET Activated=? WHERE Id=?")) {
            ps.setBoolean(1, on); ps.setInt(2, id); ps.executeUpdate();
        }
    }
    public void setRole(int id, boolean admin) throws Exception { // true=admin,false=reporter
        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement("UPDATE Users SET Role=? WHERE Id=?")) {
            ps.setBoolean(1, admin); ps.setInt(2, id); ps.executeUpdate();
        }
    }

}
