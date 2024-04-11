public class DbCon2 {
    import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCon2 {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");	//jdbc 로드
			System.out.println("JDBC Driver 로드완료");	
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "ADAM";
			String pw = "1234";
			String sql = "SELECT * FROM EMP";
			conn = DriverManager.getConnection(url,user,pw);	//db연결
			System.out.println("DB연결완료");
			
			//data 읽어오기
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String en = rs.getString("ENAME"); 
				String em = rs.getString("EMPNO"); 
				String job = rs.getString("JOB"); 
				String mgr = rs.getString("MGR"); 
				String hire = rs.getString("HIREDATE"); 
				String sal = rs.getString("SAL"); 
				String comm = rs.getString("COMM"); 
				String deptno = rs.getString("DEPTNO"); 
				System.out.println(en +" "+ em+" "+ job+" "+ mgr+" "+ hire+" "+ sal+" "+ comm+" "+ deptno);
				
			}
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
}
