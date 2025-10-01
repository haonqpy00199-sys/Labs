package dao;

import model.Newsletter;
import util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsletterDAO {

    public void subscribe(String email) throws Exception {
        String sql = "MERGE Newsletters AS t USING (SELECT ? AS Email) s ON (t.Email=s.Email) " +
                     "WHEN MATCHED THEN UPDATE SET Enabled=1 WHEN NOT MATCHED THEN INSERT(Email,Enabled) VALUES(s.Email,1);";
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.executeUpdate();
        }
    }

    public void unsubscribe(String email) throws Exception {
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement("UPDATE Newsletters SET Enabled=0 WHERE Email=?")) {
            ps.setString(1, email);
            ps.executeUpdate();
        }
    }

    public List<Newsletter> findAll() throws Exception {
        try (Connection cn = DB.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT Email,Enabled FROM Newsletters ORDER BY Email");
             ResultSet rs = ps.executeQuery()) {
            List<Newsletter> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Newsletter(rs.getString("Email"), rs.getBoolean("Enabled")));
            }
            return list;
        }
    }
}
