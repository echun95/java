package kr.or.ddit.basic;

public class T01_ThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i = 1; i<=200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		
		for (int i = 0; i <= 200; i++) {
			System.out.println("$");
		}
	}

}
