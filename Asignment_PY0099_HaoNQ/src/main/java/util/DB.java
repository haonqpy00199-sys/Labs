package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {
    private static volatile boolean loaded = false;
    private static String url, user, pass;

    private static void ensureLoaded() {
        if (loaded) return;
        synchronized (DB.class) {
            if (loaded) return;
            try {
                Properties p = new Properties();
                InputStream in = null;

                // thử nhiều cách để tìm resource trên classpath
                ClassLoader ctx = Thread.currentThread().getContextClassLoader();
                if (ctx != null) in = ctx.getResourceAsStream("db.properties");
                if (in == null) in = DB.class.getClassLoader().getResourceAsStream("db.properties");
                if (in == null) in = DB.class.getResourceAsStream("/db.properties");

                if (in == null) {
                    throw new IllegalStateException("db.properties NOT found on classpath");
                }

                p.load(in);
                url  = p.getProperty("db.url");
                user = p.getProperty("db.user");
                pass = p.getProperty("db.pass");

                if (url == null || user == null || pass == null) {
                    throw new IllegalStateException("Missing key in db.properties (need db.url, db.user, db.pass)");
                }

                // có hay không cũng được, thêm cho chắc
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                loaded = true;
            } catch (Exception e) {
                // log nguyên nhân thật chi tiết, nhưng KHÔNG làm hỏng nạp lớp
                throw new RuntimeException("Failed to load DB config: " + e.getMessage(), e);
            }
        }
    }

    public static Connection getConnection() throws Exception {
        ensureLoaded();
        return DriverManager.getConnection(url, user, pass);
    }

    // tiện để /db-check in debug
    public static String info() {
        return "loaded=" + loaded + ", url=" + url + ", user=" + user;
    }
}
