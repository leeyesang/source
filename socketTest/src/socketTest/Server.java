package socketTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Server {

	public static void main(String[] args) throws IOException {

		System.out.println("서버가 오픈되었습니다.");

		// server is listening on port 13579
		ServerSocket server_socket = new ServerSocket(13579);
		int i = 0;

		while (true) { // socket 연결대기 roof
			Socket socket = null;

			i++;

			try {
				socket = server_socket.accept(); // 서버 생성 , Client 접속 대기
				System.out.println("==========================================================");
				System.out.println("새로운 접속 클라이언트 : " + socket);

				BufferedReader in = null; // Client로부터 데이터를 읽어들이기 위한 입력스트림
				PrintWriter out = null; // Client로 데이터를 내보내기 위한 출력 스트림

				in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "EUC-KR")); // 입력스트림 생성
				out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "EUC-KR"))); // 출력스트림 생성
																														

				System.out.println(i + "번 스레드 생성");

				// create a new thread object
				Thread t = new ServertHandler(socket, in, out);

				// Invoking the start() method
				t.start();

			} catch (Exception e) {
				socket.close();
				e.printStackTrace();
			}
		}
	}
}