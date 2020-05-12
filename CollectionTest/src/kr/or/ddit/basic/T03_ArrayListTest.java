package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*문제) 5명의 사람이름을 입력하여 ArrayList에 저장하고 이 중에 '김'씨 성의 이름을 출력하시오.
      (단, 입력은 Scanner를 이용하여 입력받는다.)
*/
public class T03_ArrayListTest {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		Scanner scan = new Scanner(System.in);
		System.out.println("5명의 이름을 입력하세요");
		list.add(scan.nextLine());
		list.add(scan.nextLine());
		list.add(scan.nextLine());
		list.add(scan.nextLine());
		list.add(scan.nextLine());
		
		System.out.println("리스트 값 : " + list);
		for(String name : list) {
			if(name.substring(0,1).equals("김")) {
				System.out.println(name);
			}
			
		}
	}
}
