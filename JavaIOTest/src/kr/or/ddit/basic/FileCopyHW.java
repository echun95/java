package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopyHW {

	public static void main(String[] args) throws IOException {
		
		FileWriter fw = new FileWriter("d:/D_Other/copy_inputoutput.txt");
		FileReader fr = new FileReader("d:/D_Other/inputoutput.txt");
		
		 
		 int c;
		 
		 while((c = fr.read()) != -1) {
			fw.write(c);
		 }
		 System.out.println("복사완료");
		 fr.close();
		 fw.close();
	}

}
