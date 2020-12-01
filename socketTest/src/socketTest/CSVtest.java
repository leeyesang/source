package socketTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CSVtest {// table을 select 후 담아서 .csv 파일로 변환

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws IOException {

		Connection con; // DB 커넥션 용도
		PreparedStatement selpst = null; // SELECT QY
		ResultSet rs; // 결과를 담을 용도

		String filePath = "C:\\new create csv\\";// 파일경로
		String createFile = "Mach_info.csv";// 파일이름
		String makePath = filePath + createFile;// 전체 파일 경로+이름

		String line = ""; // list를 한 줄씩 읽어 담을 변수

		File folder = new File(filePath);// 폴더 경로 담고

		con = JdbcConnection.getConnection();// JDBC class 가져와 연결

		String squery = "SELECT COMP_ID, MACH_CD, NVL(MACH_NM,' ')MACH_NM, NVL(MACH_SIZE,' ')MACH_SIZE, NVL(MACH_SPEC,' ')MACH_SPEC ,"
				+ "NVL(CAPA,0)CAPA, NVL(UNIT,' ')UNIT, NVL(PROC_CD,' ')PROC_CD, NVL(MACH_KIND,' ')MACH_KIND, NVL(MACH_CAPCT,' ')MACH_CAPCT,"
				+ "NVL(BUY_AMT,0)BUY_AMT, NVL(CUST_CD,' ')CUST_CD, NVL(TEL_NO,' ')TEL_NO, NVL(EMP_NO,' ')EMP_NO, NVL(HP_NO,' ')HP_NO, NVL(MANU_CD,' ')MANU_CD,"
				+ "NVL(BUY_DATE,' ')BUY_DATE, NVL(USE_YN,' ')USE_YN, NVL(USE_END_REASON,' ')USE_END_REASON, NVL(SET_SEQ,' ')SET_SEQ, NVL(TANK,' ')TANK,"
				+ "NVL(MACH_TYPE,' ')MACH_TYPE, NVL(IP_AD,' ')IP_AD,  NVL(WORK_TEAM,' ')WORK_TEAM, NVL(AF_MACH_NM,' ')AF_MACH_NM, NVL(FILE_NM,' ')FILE_NM,"
				+ "NVL(REAL_FILE_NM,' ')REAL_FILE_NM, NVL(ASSSET_NO,' ')ASSSET_NO, NVL(INSP_PLAN_YN,' ')INSP_PLAN_YN, NVL(MODEL_NM,' ')MODEL_NM,"
				+ "NVL(RES_USE_YN,' ')RES_USE_YN, NVL(DOUBLE_CHK,' ')DOUBLE_CHK, NVL(PC_IP,' ')PC_IP, NVL(MACH_PROD_STATUS,' ')MACH_PROD_STATUS,"
				+ "NVL(REG_USER_ID,' ')REG_USER_ID, DECODE(REG_DATE,NULL,' ')REG_DATE, NVL(UPD_USER_ID,' ')UPD_USER_ID , DECODE(UPD_DATE,NULL,' ')UPD_DATE FROM TB_BAS_RES_1";
		// SELECT QRY 데이터 전체를 불러옴
		// null은 전부 ' '으로 치환
		// date 부분 보완 필요

		try {// 쿼리 결과 ResultSet에 담음

			if (!folder.exists()) {
				folder.mkdirs(); // 폴더가 없을경우 폴더 생성
			}

			selpst = con.prepareStatement(squery);// select QY
			rs = selpst.executeQuery();

			List<Csvbean> stList = new ArrayList<Csvbean>(); // bean 사용으로 arraylist 생성

			FileWriter fw = new FileWriter(makePath);// 파일 쓰기

			while (rs.next()) {

				Csvbean slbean = new Csvbean();

				// bean class로 불러와 담음
				slbean.setComp_id(rs.getNString("COMP_ID"));
				slbean.setMach_cd(rs.getNString("MACH_CD"));
				slbean.setMach_nm(rs.getNString("MACH_NM"));
				slbean.setMach_size(rs.getNString("MACH_SIZE"));
				slbean.setMach_spec(rs.getNString("MACH_SPEC"));
				slbean.setCapa(rs.getNString("CAPA"));
				slbean.setUnit(rs.getNString("UNIT"));
				slbean.setProc_cd(rs.getNString("PROC_CD"));
				slbean.setMach_kind(rs.getNString("MACH_KIND"));
				slbean.setMach_capct(rs.getNString("MACH_CAPCT"));
				slbean.setBuy_amt(rs.getNString("BUY_AMT"));
				slbean.setCust_cd(rs.getNString("CUST_CD"));
				slbean.setTel_no(rs.getNString("TEL_NO"));
				slbean.setEmp_no(rs.getNString("EMP_NO"));
				slbean.setHp_no(rs.getNString("HP_NO"));
				slbean.setManu_cd(rs.getNString("MANU_CD"));
				slbean.setBuy_date(rs.getNString("BUY_DATE"));
				slbean.setUse_yn(rs.getNString("USE_YN"));
				slbean.setUse_end_reason(rs.getNString("USE_END_REASON"));
				slbean.setFile_nm(rs.getNString("FILE_NM"));
				slbean.setReal_file_nm(rs.getNString("REAL_FILE_NM"));
				slbean.setSet_seq(rs.getNString("SET_SEQ"));
				slbean.setTank(rs.getNString("TANK"));
				slbean.setMach_type(rs.getNString("MACH_TYPE"));
				slbean.setIp_ad(rs.getNString("IP_AD"));
				slbean.setWork_team(rs.getNString("WORK_TEAM"));
				slbean.setAf_mach_nm(rs.getNString("AF_MACH_NM"));
				slbean.setAssset_no(rs.getNString("ASSSET_NO"));
				slbean.setInsp_plan_yn(rs.getNString("INSP_PLAN_YN"));
				slbean.setModel_nm(rs.getNString("MODEL_NM"));
				slbean.setRes_use_yn(rs.getNString("RES_USE_YN"));
				slbean.setDouble_chk(rs.getNString("DOUBLE_CHK"));
				slbean.setPc_ip(rs.getNString("PC_IP"));
				slbean.setMach_prod_status(rs.getNString("MACH_PROD_STATUS"));
				slbean.setReg_user_id(rs.getNString("REG_USER_ID"));
				slbean.setReg_date(rs.getString("REG_DATE"));
				slbean.setUpd_user_id(rs.getNString("UPD_USER_ID"));
				slbean.setUpd_date(rs.getString("UPD_DATE"));

				stList.add(slbean);// List에 담음

			}

			int i; // 한 라인이 될 예정
			
			//첫줄 컬럼명
			fw.append("사업장,");fw.append("설비코드,");fw.append("설비명,");fw.append("규격,");fw.append("설비명 규격,");
			fw.append("설비capacity,");fw.append("설비 capacity 단위,");fw.append("제조공정코드,");fw.append("설비종류,");
			fw.append("용량,");fw.append("구입금액,");fw.append("공급업체,");fw.append("전화번호,");fw.append("담당자,");
			fw.append("핸드폰번호,");fw.append("제작업체,");fw.append("구매일자,");fw.append("사용유무,");fw.append("사용중지사유,");
			fw.append("파일이름,");fw.append("실제파일이름,");fw.append("SEQ,");fw.append("사용탱크,");fw.append("유형,");
			fw.append("설비별 아이피 주소,");fw.append("작업팀 구분,");fw.append("유도설비명,");fw.append("자산번호,");
			fw.append("정기점검 계획유무,");fw.append("모델명,");fw.append("설비사용 유무,");fw.append("이중사출,");
			fw.append("pc_ip,");fw.append("설비생산상태,");fw.append("등록자,");fw.append("등록날짜,");fw.append("수정자,");
			fw.append("수정날짜,");fw.append('\n');

			for (i = 0; i < stList.size(); i++) {// list의 사이즈 만큼 반복

				// line에 한줄이 될 컬럼들을 담고 "," 추가
				line = stList.get(i).getComp_id() + "," + stList.get(i).getMach_cd() + "," + stList.get(i).getMach_nm()
						+ "," + stList.get(i).getMach_size() + "," + stList.get(i).getMach_spec() + ","
						+ stList.get(i).getCapa() + "," + stList.get(i).getUnit() + "," + stList.get(i).getProc_cd()
						+ "," + stList.get(i).getMach_kind() + "," + stList.get(i).getMach_capct() + ","
						+ stList.get(i).getBuy_amt() + "," + stList.get(i).getCust_cd() + ","
						+ stList.get(i).getTel_no() + "," + stList.get(i).getEmp_no() + "," + stList.get(i).getHp_no()
						+ "," + stList.get(i).getManu_cd() + "," + stList.get(i).getBuy_date() + ","
						+ stList.get(i).getUse_yn() + "," + stList.get(i).getUse_end_reason() + ","
						+ stList.get(i).getFile_nm() + "," + stList.get(i).getReal_file_nm() + ","
						+ stList.get(i).getSet_seq() + "," + stList.get(i).getTank() + ","
						+ stList.get(i).getMach_type() + "," + stList.get(i).getIp_ad() + ","
						+ stList.get(i).getWork_team() + "," + stList.get(i).getAf_mach_nm() + ","
						+ stList.get(i).getAssset_no() + "," + stList.get(i).getInsp_plan_yn() + ","
						+ stList.get(i).getModel_nm() + "," + stList.get(i).getRes_use_yn() + ","
						+ stList.get(i).getDouble_chk() + "," + stList.get(i).getPc_ip() + ","
						+ stList.get(i).getMach_prod_status() + "," + stList.get(i).getReg_user_id() + ","
						+ stList.get(i).getReg_date() + "," + stList.get(i).getUpd_user_id() + ","
						+ stList.get(i).getUpd_date();

				// ex)HEUNGIL,DH-I-404,호퍼드라이어4,Ø455×1150+(206), ,1087,EA,L00, ,DS[50kg],0,163,
				// ,2019012, ,163,20150802,Y, ,04호기.JPG,DH-I-404.JPG, , ,7, ,1, , ,0, ,N,N, ,9
				System.out.println(i + "번째 : " + line);
				fw.append(line);// 파일에 라인을 담고
				fw.append('\n');// 다음줄로 입력할 구분자.
			}
			System.out.println(i + "건 입력완료");
			fw.flush();// 쓰고
			fw.close();// 닫아야 파일 읽기 가능

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
