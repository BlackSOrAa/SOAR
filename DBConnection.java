package DB;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			 Class.forName("org.mariadb.jdbc.Driver"); //드라이버 객체화
			 String url = "jdbc:mariadb://3.38.112.237:3306/mysql";
			 conn = DriverManager.getConnection(url, "root", "drx2312##"); //DB와 연결
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
