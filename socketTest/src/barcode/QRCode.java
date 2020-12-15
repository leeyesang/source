package barcode;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCode {

	public static void main(String ar[]) {
		String filePath = "C:\\Users\\yah91\\Pictures\\Barcode\\";// 파일경로
		try {
			File file = null;
			// 큐알이미지를 저장할 디렉토리 지정
			file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 코드인식시 링크걸 URL주소
			String codeurl = new String("https://postfiles.pstatic.net/MjAyMDA4MTBfMjYw/MDAxNTk2OTg3NzU4ODMx.QV7-gK1BzUDhEkoCHtcDHZhWzdM096qpQK7zbG2MexUg.6VCyD7EmAUpsJTTSnEnswu-AudRMAYg5-9AL_sKrPzcg.JPEG.ynk8037/1596987757910.jpg?type=w580".getBytes("UTF-8"), "ISO-8859-1");
			// 큐알코드 바코드 생성값
			int qrcodeColor = 0xff020100;
			// 큐알코드 배경색상값
			int backgroundColor = 0xFFFFFFFF;

			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			// 3,4번째 parameter값 : width/height값 지정
			BitMatrix bitMatrix = qrCodeWriter.encode(codeurl, BarcodeFormat.QR_CODE, 200, 200);
			//
			MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(qrcodeColor, backgroundColor);
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
			// ImageIO를 사용한 바코드 파일쓰기
			ImageIO.write(bufferedImage, "png", new File(filePath+"nomaster.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
