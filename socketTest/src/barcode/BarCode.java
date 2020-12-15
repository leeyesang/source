package barcode;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;

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
	
		String format ="png";
		int nw = 250;
		int nh = 200;
		String Mposition ="X";
		
		Image image;
		int imagew;
		int imageh;
		double ratio;
		int ow;
		int oh;
		
		String str = null;// Brocade 내용
		
		try {
			String item_cd = "123456781234";// ex)전방지지대용접물
			
			int i =0;
			
				str = item_cd;// 바코드 내용
				System.out.println("제품 바코드 : " + str);
				
				String createimg = str + ".png";// 파일이름
				
				String makePath = filePath + createimg;// 전체 파일 경로+이름
				System.out.println("저장경로 : "+ makePath);
				
				Barcode bac = BarcodeFactory.createCode128B(str);// 바코드에 담음
				
				File file = new File(makePath);// 바코드 생성
				
				bac.setBarHeight(50);
				bac.setBarWidth(1);
				bac.setBorder(null);

				BarcodeImageHandler.savePNG(bac, file);// 바코드 저장
				
				i++;
			
				/*
				 * image =ImageIO.read(new File(makePath)); imagew = image.getWidth(null);
				 * imageh = image.getHeight(null);
				 * 
				 * if(Mposition.equals("W")) { ratio =(double)nw/(double)imagew; ow
				 * =(int)(imagew * ratio); oh =(int)(imageh * ratio);
				 * 
				 * }else if(Mposition.equals("H")) { ratio =(double)nh/(double)imageh; ow
				 * =(int)(imagew * ratio); oh =(int)(imageh * ratio); }else { ow =nw; oh =nh; }
				 * 
				 * Image resizeImage = image.getScaledInstance(ow, oh, Image.SCALE_DEFAULT);
				 * 
				 * BufferedImage newimage = new BufferedImage(ow, oh,
				 * BufferedImage.TYPE_INT_RGB); Graphics g = newimage.getGraphics();
				 * g.drawImage(resizeImage,0,0,null); g.dispose(); ImageIO.write(newimage,
				 * format, new File(makePath));
				 */
			System.out.println(i+"개의 제품 바코드 생성 완료");
		} catch (Exception e) {
			
		}
	}

}
