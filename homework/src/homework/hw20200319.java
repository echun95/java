package homework;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
문제) 이름, 주소, 전화번호 속성을 갖는 Phone클래스를 만들고, 이 Phone클래스를 이용하여 
	  전화번호 정보를 관리하는 프로그램을 완성하시오.
	  이 프로그램에는 전화번호를 등록, 수정, 삭제, 검색, 전체출력하는 기능이 있다.
	  
	  전체의 전화번호 정보는 Map을 이용하여 관리한다.
	  (key는 '이름'으로 하고 value는 'Phone클래스의 인스턴스'로 한다.)


실행예시)
===============================================
   전화번호 관리 프로그램(파일로 저장되지 않음)
===============================================

  메뉴를 선택하세요.
  1. 전화번호 등록
  2. 전화번호 수정
  3. 전화번호 삭제
  4. 전화번호 검색
  5. 전화번호 전체 출력
  0. 프로그램 종료
  번호입력 >> 1  <-- 직접 입력
  
  새롭게 등록할 전화번호 정보를 입력하세요.
  이름 >> 홍길동  <-- 직접 입력
  전화번호 >> 010-1234-5678  <-- 직접 입력
  주소 >> 대전시 중구 대흥동 111  <-- 직접 입력
  
  메뉴를 선택하세요.
  1. 전화번호 등록
  2. 전화번호 수정
  3. 전화번호 삭제
  4. 전화번호 검색
  5. 전화번호 전체 출력
  0. 프로그램 종료
  번호입력 >> 5  <-- 직접 입력
  
  =======================================
  번호   이름       전화번호         주소
  =======================================
   1    홍길동   010-1234-5678    대전시
   ~~~~~
   
  =======================================
  출력완료...
  
  메뉴를 선택하세요.
  1. 전화번호 등록
  2. 전화번호 수정
  3. 전화번호 삭제
  4. 전화번호 검색
  5. 전화번호 전체 출력
  0. 프로그램 종료
  번호입력 >> 0  <-- 직접 입력
  
  프로그램을 종료합니다...
  
*/
public class hw20200319 {
	private Scanner scan;
	private Map<String, Room> hotel; 
	
	public hw20200319() {
		scan = new Scanner(System.in);
		hotel = new HashMap<>();
	}
	
	// 메뉴를 출력하는 메서드
	public void displayMenu(){
		System.out.println();
		System.out.println("*****************");	
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.println("*****************");		
		System.out.print(" 메뉴선택 >> ");		
	}
	
	// 프로그램을 시작하는 메서드
	public void hotelStart(){
		System.out.println("===============================================");
		System.out.println(" 호텔 문을 열었습니다. ");
		System.out.println("===============================================");
		
		while(true){
			
			displayMenu();  // 메뉴 출력
			
			int menuNum = scan.nextInt();   // 메뉴 번호 입력
			
			switch(menuNum){
				case 1 : insert();		// 등록
					break;
				case 2 : delete();		// 삭제
					break;			
				case 3 : displayAll();	// 전체 출력
					break;
				case 4 :
					System.out.println("*****************");	
					System.out.println("호텔 문을 닫았습니다.");
					System.out.println("*****************");	
					return;
				default :
					System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}
	/*
	 * 새로운 전화번호 정보를 등록하는 메서드
	 * 이미 등록된 사람은 등록되지 않는다.
	 */
	private void insert() {
			System.out.println("어느방에 체크인 하시겠습니까?");
			System.out.print("방번호 입력 >>");
			String num = scan.next();			
			
			scan.nextLine();

			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력 >>");
			String name = scan.next();
			
			if(hotel.get(num) != null) {
				System.out.println(num + "방에는 이미 사람이 있습니다.");
				return;
			}			

			hotel.put(num, new Room(num,name));
			System.out.println("체크인 되었습니다.");
	}
	/*
	 * 전체 자료를 출력하는 메소드
	 */
	private void displayAll() {
		Set<String> keySet = hotel.keySet();
	
		if(keySet.size()==0) {
			System.out.println("체크인 된 방이 없습니다.");
		}
		else {
			Iterator<String> it = keySet.iterator();
			
			while(it.hasNext()) {				
				String num = it.next();//키값꺼내기
				Room p = hotel.get(num);
				System.out.println("방 번호 : " + num + "   투숙객 : " + p.getName());
			}			
		}
		
	}	
	
	/*
	 * 전화번호 정보를 삭제하는 메서드
	 */
	private void delete() {
		System.out.println("어느 방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 >>");
		String num = scan.next();
		if(hotel.remove(num)==null) {
			System.out.println(num + "방에는 체크인한 사람이 없습니다.");
		}
		else {
			System.out.println("체크아웃 되었습니다.");
		}
	}
	
	
	public static void main(String[] args) {
		new hw20200319().hotelStart();
	}
	
}

/*
 * 전화번호 정보를 저장하기 위한 VO클래스
 */
class Room{
	private String num;
	private String name;
	
	public Room(String num, String name) {
		super();
		this.num = num;
		this.name = name;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}

