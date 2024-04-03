package day04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {
	static InputStream is = null;
	static OutputStream out = null;
	
	public static void main(String[] args) throws Exception{
		String originalFilename = "./image.jpg";
		String copyFilename = "./copy.jpg";
		
		is = new FileInputStream(originalFilename);
		out = new FileOutputStream(copyFilename);
		
		byte[] img = new byte[1024];
		while(true) {
			int size = is.read(img);
			if(size == -1) break;
			out.write(img, 0, size);
		}
		out.flush();
		out.close();
		is.close();
		
		System.out.println("복사완료");
}
}