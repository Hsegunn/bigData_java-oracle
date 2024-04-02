package day04;

import java.io.*;

public class ReadLineEx {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(
			new FileReader("src/com/file/ReadLineEx.java")
			);
		int lineNo = 1;
		while(true) {
			String str = br.readLine();
			if(str == null) break;
			System.out.println(lineNo + "\t" + str);
		}
		br.close();
	}
}
