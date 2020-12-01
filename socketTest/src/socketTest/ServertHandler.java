package socketTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServertHandler extends Thread {

	final BufferedReader in;
	final PrintWriter out;
	final Socket socket;

	public ServertHandler(Socket socket, BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		this.socket = socket; 

	}

	@Override
	public void run() {
		Connection con = null; // DB 커넥션 용도
		PreparedStatement selpst = null; // SELECT QY
		PreparedStatement inspst = null; // INSERT QY
		PreparedStatement inspst2 = null; // Sub INSERT QY
		PreparedStatement updpst = null; // Sub INSERT QY

		ResultSet rs = null; // 결과를 담을 용도

		con = JdbcConnection.getConnection();// JDBC class 가져와 연결
	
		int cnt = 0; // Client에서 보낸 건 수
		int r_cnt = 0;// 테이블에서 검색된 건 수
		int incnt = 0; // insert 건수
		int upcnt = 0; // update 건수

		String line = null;// client에서 보낸 레코드를 담는 용도

		String squery1 = null; // SELECT QRY 값을 가져와서 비교하는 용도
		String squery2 = null; // 비교후 INSERT QRY
		String squery3 = null; // table2
		String squery4 = null; // Update QY

		String[] getstr = null;// 구분자로 분리된 data를 담을 Array

		try {
			squery1 = "SELECT COUNT(*) CNT FROM TB_BAS_RES_1 WHERE COMP_ID = ? AND MACH_CD = ?"; // 테이블에 동일한 값이 있는지 검색

			squery2 = "INSERT INTO TB_BAS_RES_1 (COMP_ID, MACH_CD, MACH_NM, MACH_SIZE, MACH_SPEC,"
					+ "CAPA, UNIT, PROC_CD, MACH_KIND, MACH_CAPCT, BUY_AMT, CUST_CD, TEL_NO, EMP_NO, HP_NO,"
					+ "MANU_CD, BUY_DATE, USE_YN, USE_END_REASON, FILE_NM, REAL_FILE_NM, SET_SEQ, TANK, "
					+ "MACH_TYPE, IP_AD, WORK_TEAM, AF_MACH_NM, ASSSET_NO, INSP_PLAN_YN, MODEL_NM, RES_USE_YN, "
					+ "DOUBLE_CHK, PC_IP, MACH_PROD_STATUS)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // 메인 테이블에 저장하는 QY

			squery3 = "INSERT INTO TB_BAS_RES_FLAG_1 (COMP_ID, MACH_CD, REG_USER_ID, REG_DATE)"
					+ "VALUES (?,?,'SYSTEM',SYSDATE)"; // 서브 테이블에 저장하는 QY

			squery4 = "UPDATE TB_BAS_RES_1 SET COMP_ID = ?, MACH_CD = ?, MACH_NM = ?, MACH_SIZE = ?, MACH_SPEC = ?,"
					+ "CAPA = ?, UNIT = ?, PROC_CD = ?, MACH_KIND = ?, MACH_CAPCT = ?, BUY_AMT = ?, CUST_CD = ?,"
					+ "TEL_NO = ?, EMP_NO = ?, HP_NO = ?, MANU_CD = ?, BUY_DATE = ?, USE_YN = ?, USE_END_REASON = ?, FILE_NM = ?, "
					+ "REAL_FILE_NM = ?, SET_SEQ = ?, TANK = ?, MACH_TYPE = ?, IP_AD = ?, WORK_TEAM = ?, AF_MACH_NM = ?, ASSSET_NO = ?, "
					+ "INSP_PLAN_YN = ?, MODEL_NM = ?, RES_USE_YN = ?, DOUBLE_CHK = ?, PC_IP = ?, MACH_PROD_STATUS = ? WHERE COMP_ID = ? AND MACH_CD = ?";

			// con.setAutoCommit(false);// JDBC class에서 이미 선언
			
			
			cnt = 0;

			while (true) { // client의 Record 받는 roof
				line = "";
				line = in.readLine(); // Client로부터 데이터를 읽어옴
				cnt++;

				//System.out.println("Client로 부터 온 메세지 #" + cnt + " : " + line);
				System.out.print(cnt+",");
				
				if ("END".equals(line)) { // client의 end 전송시 출력 끝
					System.out.println("End Data!");
					out.println(line);
					out.flush();
					break;

				}

				getstr = line.split(",");// 구분자로 분리하여 배열에 담음
				//System.out.println("배열 확인 :"+getstr.length);

				try {// 중복체크할 용도
					selpst = con.prepareStatement(squery1);
					selpst.setNString(1, getstr[0]); // COMP_ID
					selpst.setNString(2, getstr[1]); // MACH_CD
					rs = selpst.executeQuery();

					rs.next();
					r_cnt = rs.getInt("CNT"); // 검색된 결과를 변수에 담음. ex) 1 or 0

					rs.close(); // 자원 반납
					selpst.close();// 자원 반납

				} catch (SQLException e) {
					e.printStackTrace();
				}

				inspst = con.prepareStatement(squery2);// Main Table Insert QY
				inspst2 = con.prepareStatement(squery3);// Sub Table Insert QY

				updpst = con.prepareStatement(squery4);// UPdate QY

				if (r_cnt == 0) {// SELECT해서 가져온 값이 1이면 이미 있고 0이면 없는거 없으면 insert

					for (int x = 0; x < getstr.length; x++) { // arr = getstr[0]=> x로 증가시킴

						inspst.setString(x + 1, getstr[x]); // 배열의 크기만큼 반복

					}
					incnt++;
					inspst2.setString(1, getstr[0]);// Sub Table COMP_ID
					inspst2.setString(2, getstr[1]);// Sub Table MACH_CD

					inspst.executeUpdate();
					inspst2.executeUpdate();

					inspst.close();// 반환
					inspst2.close();

				} else if (r_cnt == 1) { // update 영역

					for (int x = 0; x < getstr.length; x++) {

						updpst.setString(x + 1, getstr[x]);

					}
					updpst.setString(35, getstr[0]);// 검색조건 COMP_ID
					updpst.setString(36, getstr[1]);// 검색조건 MACH_CD

					upcnt++;
					updpst.executeUpdate();

					updpst.close();// 반환

				} // update end
				out.println("OK");
				out.flush();

			} // client to server record end
			con.commit(); // sql commit

			System.out.println("Insert 완료 : " + incnt + " 건");
			System.out.println("Update 완료 : " + upcnt + " 건");
			
			in.close();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (SQLException sqle) {
			if (con != null) {
				System.out.println("저장 실패");
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					con.setAutoCommit(true);// 자동저장 재실행
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
