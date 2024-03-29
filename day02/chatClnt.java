package day02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class chatClnt {
	static Socket socket = null;
	static BufferedReader in = null;
	static BufferedWriter out = null;
	
	public static void main(String[] args) {
		startConn();
	}
	public static void startConn() {
		Scanner sc = new Scanner(System.in);
		try {
			socket = new Socket("210.119.14.67", 8080);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				System.out.print("> ");
				String txMsg = sc.nextLine();	// 서버로 전송할 메세지 저장
				if(txMsg.equals("bye")) {
					out.write(txMsg+"\n");
					out.flush();
					break;
				}
				out.write(txMsg + "\n");
				out.flush();
				
				String rxMsg = in.readLine();
				System.out.println("[server]" + rxMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sc.close();
			try {
				out.close();
				in.close();
				socket.close();
				sc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}