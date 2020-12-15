package barcode;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BarcodeReader {

	public static void main(String[] args) {
		String barcodePath = "C:\\Users\\yah91\\Pictures\\Barcode\\";
		String Image = "1438412084_milk100"+".png";
		
		String PullTem =barcodePath+Image;
		
		System.out.println(PullTem);
		
		try {
			Process p = Runtime.getRuntime().exec(PullTem);
			
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			String line =reader.readLine();
			
			while (line!=null) {
				System.out.println("여기 지나감");
				System.out.println("바코드 내용"+line);
				line =reader.readLine();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
