package homework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
	private Scanner scan;
	private Map<String, HotelRoom> hotel;

	public Hotel() {
		scan = new Scanner(System.in);
		hotel = new HashMap<>();
	}

	// 메뉴를 출력하는 메서드
	public void displayMenu() {
		System.out.println();
		System.out.println("*****************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.println("*****************");
		System.out.print(" 메뉴선택 >> ");
	}

	// 프로그램을 시작하는 메서드
	public void hotelStart() {
		System.out.println("===============================================");
		System.out.println(" 호텔 문을 열었습니다. ");
		System.out.println("===============================================");

		while (true) {

			displayMenu(); // 메뉴 출력

			int menuNum = scan.nextInt(); // 메뉴 번호 입력

			switch (menuNum) {
			case 1:
				insert(); // 등록
				break;
			case 2:
				delete(); // 삭제
				break;
			case 3:
				displayAll(); // 전체 출력
				break;
			case 4:
				System.out.println("*****************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("*****************");
				return;
			default:
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			} // switch문
		} // while문
	}

	/*
	 * 새로운 전화번호 정보를 등록하는 메서드 이미 등록된 사람은 등록되지 않는다.
	 */
	private void insert() {
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 >>");
		String num = scan.next();

		scan.nextLine();

		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >>");
		String name = scan.next();

		if (hotel.get(num) != null) {
			System.out.println(num + "방에는 이미 사람이 있습니다.");
			return;
		}

		hotel.put(num, new HotelRoom(num, name));
		System.out.println("체크인 되었습니다.");
	}

	/*
	 * 전체 자료를 출력하는 메소드
	 */
	private void displayAll() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; // 쿼리문이 select일때 필요함

		// 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// DB에 접속(Connection 객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "student";
			String password = "java";

			// 실제적으로 Oracle Driver가 사용되는 부분
			conn = DriverManager.getConnection(url, userId, password);

			// Statement객체 생성 -> Connection객체를 이용한다.
			stmt = conn.createStatement();

			String sql = "select * from hotel"; // 실행할 sql문

			rs = stmt.executeQuery(sql);
			System.out.println("===쿼리문 실행 결과===");

			while (rs.next()) {
				System.out.println("방번호 : " + rs.getInt("hotel_num"));
				System.out.println("이름 : " + rs.getString("name"));
				System.out.println("---------------------------------------------------");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
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
		if (hotel.remove(num) == null) {
			System.out.println(num + "방에는 체크인한 사람이 없습니다.");
		} else {
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
class HotelRoom {
	private String num;
	private String name;

	public HotelRoom(String num, String name) {
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
