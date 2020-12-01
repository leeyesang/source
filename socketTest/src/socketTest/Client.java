package socketTest;

import java.io.*;
import java.net.*;


public class Client {
	public static void main(String[] arg)  throws InterruptedException {
		Socket socket = null; // Server와 통신하기 위한 Socket
		BufferedReader in = null; // Server로부터 데이터를 읽어들이기 위한 입력스트림
		PrintWriter out = null; // 서버로 내보내기 위한 출력 스트림
		InetAddress ia = null;

		int cnt = 0;

		// Server에 전송할 화일명
		
		File file = new File("C:\\new create csv\\Mach_info.csv");
		//File file = new File("C:\\TB_BAS_RES2.txt");

		// File Record Buffer
		String line = "";
		String str2 = "";
		
		try {

			// 입력 버퍼 생성
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "EUC-KR")); // 한글깨짐현상조치
			ia = InetAddress.getByName("127.0.0.1"); // 서버로 접속 IP 
			
			socket = new Socket(ia, 13579);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

			System.out.println(socket.toString());

			// .readLine()은 File의 한 Record를 Read
			while ((line = bufReader.readLine()) != null) {	
				
				if ( line == null) break;
				
				cnt++;
				System.out.println(cnt + ":" + line); // 파일 한줄씩 읽은 값 출력
				
				String tab = "	";
				
				if(line.contains(tab)) { //txt 형식의 파일일 경우
					String gettxt = line;
					line = gettxt.replace(tab, ",");
					
					System.out.println(line);
					
					out.println(line); 					  // 서버로 데이터 전송
					out.flush();
				
				}else {
				
					out.println(line); 					  // 서버로 데이터 전송
					out.flush();
				}
				str2 = in.readLine(); 				 // 서버로부터 되돌아오는 데이터 읽어들임
				
			}
			
			out.println("END"); // 서버로 데이터 전송
			out.flush();

			//str2 = in.readLine(); // 서버로부터 되돌아오는 데이터 읽어들임
			System.out.println("서버로부터 되돌아온 마지막 메세지 : " + str2);

			System.out.println("Finshed !");

			// 사용한 자원 닫기
			bufReader.close();
			socket.close();
			in.close();

		} catch (FileNotFoundException e) {
			System.out.println("File 로딩 실패");
		} catch (IOException e) {
			// try-catch문 오류방지
			System.out.println(e.getMessage());
		} 

	}
}