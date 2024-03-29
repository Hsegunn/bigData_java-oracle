package day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
	// 필드
	static ServerSocket serverSocket = null;
	static Socket clientSocket = null; 
	static BufferedReader in = null; 
	static BufferedWriter out = null; 
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		serverSocket = new ServerSocket(8080);
		System.out.println("서버 시작");
			
		while(true) {
			clientSocket = serverSocket.accept();	// 클라이언트 연결(데이터를 주고받는 소켓)
			System.out.printf("클라이언트 %s %d 연결\n", clientSocket.getInetAddress(), clientSocket.getPort());
			Thread clientThread = new Thread() {	// 익명객체
				@Override
				public void run() {
					try {
						in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));	// 입력
						out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));	// 출력
						while(true) {
							String rxMsg;				
							rxMsg = in.readLine();		
							if(rxMsg.equals("bye")) {
								System.out.println("클라이언트 퇴장");
								break;
							}
							System.out.println("[client] " + rxMsg);	
							System.out.println("> ");
							String txMsg = sc.nextLine();	//송신(보내기)
							out.write(txMsg+"\n");			
							out.flush();		
						} 
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
					  try {
						  out.close();
						  in.close();
						  clientSocket.close();
						  serverSocket.close();
						  sc.close();
					  }catch (IOException e) {
						  e.printStackTrace();
					  }
					}
				}
			};
			clientThread.start();
		}
	}
}