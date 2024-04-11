import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon1 {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	//jdbc 로드
			System.out.println("JDBC Driver 로드완료");	
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "ADAM";
			String pw = "1234";
			conn = DriverManager.getConnection(url,user,pw);	//db연결
			System.out.println("DB연결완료");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("DB연결해제");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
