package barcode;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import socketTest.JdbcConnection;

public class BarCode {

	public static void main(String[] args) {
		Connection con; // DB 커넥션 용도
		PreparedStatement selpst = null; // SELECT QY
		ResultSet rs; // 결과를 담을 용도

		con = JdbcConnection.getConnection();// JDBC class 가져와 연결

		String filePath = "C:\\Users\\yah91\\Pictures\\Barcode\\";// 파일경로

		// String query = "SELECT ITEM_CD, ITEM_NM FROM TB_BAS_ITEM WHERE COMP_ID =
		// 'HEUNGIL'";
		String query = "SELECT ITEM_CD, ITEM_NM FROM  TB_BAS_ITEM  WHERE COMP_ID = ? AND ITEM_CD = ?";// select QY

		String str;// Brocade 내용
		try {

			String comp_id = "HEUNGIL";
			String item_cd = "4C75301120";// ex)전방지지대용접물

			selpst = con.prepareStatement(query);// select QY
			selpst.setNString(1, comp_id); // COMP_ID
			selpst.setNString(2, item_cd); // ITEM_CD
			rs = selpst.executeQuery();
			
			int i =0;
			
			while (rs.next()) {
				//System.out.println(rs.getNString("ITEM_CD"));
				//System.out.println(rs.getNString("ITEM_NM"));
				
				String nm = rs.getNString("ITEM_NM");// 파일 이름으로 들어갈 제품명
				System.out.println("이미지 이름 : " + nm);
				
				str = rs.getNString("ITEM_CD");// 바코드 내용
				System.out.println("제품 바코드 : " + str);
				
				String createimg = nm + ".png";// 파일이름
				//System.out.println(createimg);
				
				String makePath = filePath + createimg;// 전체 파일 경로+이름
				System.out.println("저장경로 : "+ makePath);
				
				Barcode bac = BarcodeFactory.createCode128B(str);// 바코드에 담음
				
				File file = new File(makePath);// 바코드 생성

				BarcodeImageHandler.savePNG(bac, file);// 바코드 저장
				
				i++;

			}
			
			System.out.println(i+"개의 제품 바코드 생성 완료");
		} catch (Exception e) {
		}
	}

}
