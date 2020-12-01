package socketTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JdbcConnection {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}

	// 오라클에서 커넥션 객체를 받아서 반환
	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@dtaas.iptime.org:1521:mesd", "DTSMES", "dtsmes12");
			con.setAutoCommit(false); //수동 커밋 하겠다.
			System.out.println("Connection Success!");
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return con;
	}
	
	// commit
	public static void commit(Connection con) {
		try {
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// rollback
	public static void rollback(Connection con) {
		try {
			con.rollback();
		} catch (Exception e) {
		}
	}

}