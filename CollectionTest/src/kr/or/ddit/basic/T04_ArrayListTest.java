package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*문제1) 5명의 별명을 입력하여 ArrayList에 저장하고 별명의 길이가 제일 긴 별명을 출력하시오.
 * 		(단, 각 별명의 길이는 모두 다르게 입력한다.)
 *문제2) 문제 1에서 별명의 길이가 같은 것을 여러개 입력 했을 때에도 처리 되도록 하시오.*/

public class T04_ArrayListTest {
	
	
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		System.out.println("5명의 별명을 입력하세요");
		list.add(scan.nextLine());
		list.add(scan.nextLine());
		list.add(scan.nextLine());
		list.add(scan.nextLine());
		list.add(scan.nextLine());
		
		System.out.println("리스트 값 : " + list);
		String name = list.get(0);
		int length = list.get(0).length();
		
		for (int i = 0; i < list.size()-1; i++) {
			if(list.get(i).length() <= list.get(i+1).length()) {
				name = list.get(i+1);				
			}
		}		
		System.out.println("가장 긴 별명은 : " + name);
		
		
		
		name = list.get(0);
		length = list.get(0).length();
		
		for (int i = 0; i < list.size()-1; i++) {
			if(list.get(i).length() <= list.get(i+1).length()) {				
				length = list.get(i+1).length();
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).length() == length) {
				System.out.println("가장 긴 별명은 : " + list.get(i));				
			}
		}
	}
}
