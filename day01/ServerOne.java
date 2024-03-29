package day01;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerOne {  // 멤버변수 , 멤버함수(메서드), 생성자
	static ServerSocket serverSocket = null;	// 맴버변수
	public static void main(String[] args) {
		System.out.println("---------------------------------");
		System.out.println("서버를 종료 q 또는 Q를 입력하고 Enter 키를 입력");
		System.out.println("---------------------------------");
		
		startServer();  // 메서드 호출
		// sc : 참조변수(객체의 주소를 저장)
		// new 키워드를 통해서 heap영역에 객체를 생성하고 생성된 객체의 주소를 반환
		Scanner sc = new Scanner(System.in);
		while(true) {
			String key = sc.nextLine(); // String은 객체, 기본자료형은 래퍼클래스로 객체로 변경가능
			if(key.toLowerCase().equals("q")) {
				break;
			}
		}
		System.out.println("프로그램 종료");
		sc.close();
		stopServer();
	}
	
	static void startServer() {  // 정적멤버(클래스멤버)로 만든다.
		Thread thread = new Thread() { 
			@Override
			public void run() { 	// 익명객체(1. 익명자식객체-상속, 2. 익명구현객체-인터페이스)
				try {
					serverSocket = new ServerSocket(8080);		// 포트번호 8080을 사용하는 서버소켓 생성
					System.out.println("서버 소켓 생성");
					
					Socket clntSocket = serverSocket.accept();	// 클라이언트 소켓생성
					System.out.println("클라이언트 소켓 생성");
					
					//서버가 클라이언트로 메세지 보내기
					DataOutputStream dos = new DataOutputStream(clntSocket.getOutputStream());
					dos.writeUTF("First my Server");
					dos.flush();
					
					clntSocket.close();
					System.out.println("클라이언트 소켓 종료");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
	}
	static void stopServer() {  // 정적멤버(클래스멤버)로 만든다.
		try {
			serverSocket.close();
			System.out.println("서버 소켓 종료");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}