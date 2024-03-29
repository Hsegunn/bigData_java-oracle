package day01;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientOne {
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8080);
			System.out.println("[clnt]서버 연결 성공");
			
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			String receive = dis.readUTF();
			System.out.printf("Receive message: %s\n", receive);
			
			socket.close();
			System.out.println("[clnt]서버 연결 종료");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}