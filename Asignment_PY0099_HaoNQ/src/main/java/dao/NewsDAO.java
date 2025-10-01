package dao;

import model.News;
import util.DB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {

	/* ====================== CREATE ====================== */
	/** Tạo bài viết mới. Trả về Id vừa tạo. */
	public int create(News n) throws Exception {
		String sql = """
				INSERT INTO News
				  (Title, Content, Image, PostedDate, Author, ViewCount, CategoryId, Home, Approved, ReporterId)
				VALUES
				  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";
		try (Connection cn = DB.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, n.getTitle());
			ps.setString(2, n.getContent());
			ps.setString(3, n.getImage());
			ps.setTimestamp(4, Timestamp.valueOf(n.getPostedDate())); // LocalDateTime -> SQL
			ps.setString(5, n.getAuthor());
			ps.setInt(6, n.getViewCount()); // thường = 0 khi tạo
			ps.setInt(7, n.getCategoryId());
			ps.setBoolean(8, n.isHome());
			ps.setBoolean(9, n.isApproved());
			if (n.getReporterId() == null)
				ps.setNull(10, Types.INTEGER);
			else
				ps.setInt(10, n.getReporterId());

			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				return rs.next() ? rs.getInt(1) : 0;
			}
		}
	}

	// ===== Public APIs dùng cho HomeServlet =====

	/** Lấy danh sách bài đặt Trang nhất (Home = 1), đã duyệt, mới nhất trước. */
	public List<News> findHome(int limit) throws Exception {
		String sql = "SELECT Id, Title, [Content], [Image], PostedDate, Author, ViewCount, "
				+ "       CategoryId, [Home], Approved, ReporterId " + "FROM News "
				+ "WHERE Approved = 1 AND [Home] = 1 " + "ORDER BY PostedDate DESC " + "OFFSET 0 ROWS FETCH NEXT "
				+ Math.max(0, limit) + " ROWS ONLY";
		try (Connection cn = DB.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			List<News> list = new ArrayList<>();
			while (rs.next())
				list.add(map(rs));
			return list;
		}
	}

	/** Top N hot theo ViewCount giảm dần, chỉ lấy bài đã duyệt. */
	public List<News> findTopHot(int limit) throws Exception {
		String sql = "SELECT Id, Title, [Content], [Image], PostedDate, Author, ViewCount, "
				+ "       CategoryId, [Home], Approved, ReporterId " + "FROM News " + "WHERE Approved = 1 "
				+ "ORDER BY ViewCount DESC, PostedDate DESC " + "OFFSET 0 ROWS FETCH NEXT " + Math.max(0, limit)
				+ " ROWS ONLY";
		try (Connection cn = DB.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			List<News> list = new ArrayList<>();
			while (rs.next())
				list.add(map(rs));
			return list;
		}
	}

	/** Top N mới theo PostedDate, chỉ lấy bài đã duyệt. */
	public List<News> findTopNew(int limit) throws Exception {
		String sql = "SELECT Id, Title, [Content], [Image], PostedDate, Author, ViewCount, "
				+ "       CategoryId, [Home], Approved, ReporterId " + "FROM News " + "WHERE Approved = 1 "
				+ "ORDER BY PostedDate DESC " + "OFFSET 0 ROWS FETCH NEXT " + Math.max(0, limit) + " ROWS ONLY";
		try (Connection cn = DB.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			List<News> list = new ArrayList<>();
			while (rs.next())
				list.add(map(rs));
			return list;
		}
	}

	/** Lấy 1 bài theo Id (có thể dùng cả khi chưa duyệt, tuỳ luồng). */
	public News findById(int id) throws Exception {
		String sql = "SELECT Id, Title, [Content], [Image], PostedDate, Author, ViewCount, "
				+ "       CategoryId, [Home], Approved, ReporterId " + "FROM News WHERE Id = ?";
		try (Connection cn = DB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() ? map(rs) : null;
			}
		}
	}

	// ===== (Tuỳ chọn) Hữu ích cho phần khác =====

	/** Tăng view 1 đơn vị (gọi trong NewsDetailServlet). */
	public void increaseView(int id) throws Exception {
		try (Connection cn = DB.getConnection();
				PreparedStatement ps = cn.prepareStatement("UPDATE News SET ViewCount = ViewCount + 1 WHERE Id = ?")) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	/** Truy vấn theo Category (đã duyệt), có phân trang. */
	public List<News> findByCategory(int categoryId, int offset, int pageSize) throws Exception {
		String sql = "SELECT Id, Title, [Content], [Image], PostedDate, Author, ViewCount, "
				+ "       CategoryId, [Home], Approved, ReporterId "
				+ "FROM News WHERE Approved = 1 AND CategoryId = ? " + "ORDER BY PostedDate DESC "
				+ "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
		try (Connection cn = DB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, categoryId);
			ps.setInt(2, Math.max(0, offset));
			ps.setInt(3, Math.max(0, pageSize));
			try (ResultSet rs = ps.executeQuery()) {
				List<News> list = new ArrayList<>();
				while (rs.next())
					list.add(map(rs));
				return list;
			}
		}
	}

	/** Tìm kiếm tiêu đề/nội dung (đã duyệt). */
	public List<News> search(String keyword, int limit) throws Exception {
		String kw = (keyword == null ? "" : keyword.trim());
		String sql = "SELECT Id, Title, [Content], [Image], PostedDate, Author, ViewCount, "
				+ "       CategoryId, [Home], Approved, ReporterId "
				+ "FROM News WHERE Approved = 1 AND (Title LIKE ? OR [Content] LIKE ?) " + "ORDER BY PostedDate DESC "
				+ "OFFSET 0 ROWS FETCH NEXT " + Math.max(0, limit) + " ROWS ONLY";
		try (Connection cn = DB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			String like = "%" + kw + "%";
			ps.setString(1, like);
			ps.setString(2, like);
			try (ResultSet rs = ps.executeQuery()) {
				List<News> list = new ArrayList<>();
				while (rs.next())
					list.add(map(rs));
				return list;
			}
		}
	}

	// các bài đã duyệt theo chuyên mục, mới nhất trước
	public List<News> findByCategory(int categoryId) throws Exception {
		String sql = """
				SELECT Id, Title, Content, Image, PostedDate, Author, ViewCount,
				       CategoryId, Home, Approved, ReporterId
				FROM News
				WHERE Approved = 1 AND CategoryId = ?
				ORDER BY PostedDate DESC
				""";
		try (Connection cn = DB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

			ps.setInt(1, categoryId);

			try (ResultSet rs = ps.executeQuery()) {
				List<News> list = new ArrayList<>();
				while (rs.next()) {
					list.add(map(rs));
				}
				return list;
			}
		}
	}

	/* ====================== UPDATE ====================== */
	/** Cập nhật toàn bộ trường (trừ Id). */
	public void update(News n) throws Exception {
		String sql = """
				UPDATE News
				   SET Title=?, Content=?, Image=?, PostedDate=?, Author=?,
				       ViewCount=?, CategoryId=?, Home=?, Approved=?, ReporterId=?
				 WHERE Id=?
				""";
		try (Connection cn = DB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

			ps.setString(1, n.getTitle());
			ps.setString(2, n.getContent());
			ps.setString(3, n.getImage());
			ps.setTimestamp(4, Timestamp.valueOf(n.getPostedDate()));
			ps.setString(5, n.getAuthor());
			ps.setInt(6, n.getViewCount());
			ps.setInt(7, n.getCategoryId());
			ps.setBoolean(8, n.isHome());
			ps.setBoolean(9, n.isApproved());
			if (n.getReporterId() == null)
				ps.setNull(10, Types.INTEGER);
			else
				ps.setInt(10, n.getReporterId());
			ps.setInt(11, n.getId());

			ps.executeUpdate();
		}
	}

	/* ====================== DELETE ====================== */
	public boolean delete(int id) throws Exception {
		try (Connection cn = DB.getConnection();
				PreparedStatement ps = cn.prepareStatement("DELETE FROM News WHERE Id=?")) {
			ps.setInt(1, id);
			return ps.executeUpdate() > 0;
		}
	}

	/** Lấy tất cả bài viết, mới nhất trước. */
	public List<News> findAll() throws Exception {
		String sql = """
				SELECT Id, Title, Content, Image, PostedDate, Author, ViewCount,
				       CategoryId, Home, Approved, ReporterId
				FROM News
				ORDER BY PostedDate DESC
				""";
		try (Connection cn = DB.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			List<News> list = new ArrayList<>();
			while (rs.next())
				list.add(map(rs));
			return list;
		}
	}

	/** Phân trang (page >=1, size >0). */
	public List<News> findAll(int page, int size) throws Exception {
		String sql = """
				SELECT Id, Title, Content, Image, PostedDate, Author, ViewCount,
				       CategoryId, Home, Approved, ReporterId
				FROM News
				ORDER BY PostedDate DESC
				OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
				""";
		try (Connection cn = DB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, (page - 1) * size);
			ps.setInt(2, size);
			try (ResultSet rs = ps.executeQuery()) {
				List<News> list = new ArrayList<>();
				while (rs.next())
					list.add(map(rs));
				return list;
			}
		}
	}

	public int countAll() throws Exception {
		try (Connection c = DB.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM News");
				ResultSet rs = ps.executeQuery()) {
			return rs.next() ? rs.getInt(1) : 0;
		}
	}

	public int countPending() throws Exception {
		try (Connection c = DB.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM News WHERE Approved=0");
				ResultSet rs = ps.executeQuery()) {
			return rs.next() ? rs.getInt(1) : 0;
		}
	}

	public List<News> findPending() throws Exception {
		String sql = """
				SELECT Id, Title, Content, Image, PostedDate, Author, ViewCount,
				       CategoryId, Home, Approved, ReporterId
				FROM News WHERE Approved=0 ORDER BY PostedDate DESC
				""";
		try (Connection cn = DB.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			List<News> list = new ArrayList<>();
			while (rs.next())
				list.add(map(rs));
			return list;
		}
	}

	public void setApproved(int id, boolean ok) throws Exception {
		try (Connection c = DB.getConnection();
				PreparedStatement ps = c.prepareStatement("UPDATE News SET Approved=? WHERE Id=?")) {
			ps.setBoolean(1, ok);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
	}

	public void setHome(int id, boolean on) throws Exception {
		try (Connection c = DB.getConnection();
				PreparedStatement ps = c.prepareStatement("UPDATE News SET Home=? WHERE Id=?")) {
			ps.setBoolean(1, on);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
	}

	// tìm tin đã được duyệt
	public List<News> findApproved(int limit) throws Exception {
		String sql = "SELECT TOP " + limit + " * FROM News WHERE Approved = 1 ORDER BY PostedDate DESC";
		try (Connection cn = DB.getConnection();
				PreparedStatement ps = cn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			List<News> list = new ArrayList<>();
			while (rs.next()) {
				list.add(map(rs));
			}
			return list;
		}
	}

	public void increaseViewCount(int id) throws Exception {
		String sql = "UPDATE News SET ViewCount = ViewCount + 1 WHERE Id = ?";
		try (Connection cn = DB.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		}
	}

	// ===== Mapping chung =====

	private News map(ResultSet rs) throws SQLException {
		News n = new News();
		n.setId(rs.getInt("Id"));
		n.setTitle(rs.getString("Title"));
		n.setContent(rs.getString("Content")); // cột [Content]
		n.setImage(rs.getString("Image")); // cột [Image]
		Timestamp ts = rs.getTimestamp("PostedDate");
		n.setPostedDate(ts != null ? ts.toLocalDateTime() : null);
		n.setAuthor(rs.getString("Author"));
		n.setViewCount(rs.getInt("ViewCount"));
		n.setCategoryId(rs.getInt("CategoryId"));
		n.setHome(rs.getBoolean("Home"));
		n.setApproved(rs.getBoolean("Approved"));

		int rep = rs.getInt("ReporterId");
		n.setReporterId(rs.wasNull() ? null : rep);
		return n;
	}
}
