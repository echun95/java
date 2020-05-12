package kr.or.ddit.basic;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T02_jdbcTest {
/*
 * 문제 1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다 lprod_id가 큰 자료들을 출력하시오
 * 
 * 문제 2) lprod_id값을 2개 입력 받아서 두값 중 작은 값부터 큰값 사이의 자료를 출력하시오.
 */

		
		public static void main(String[] Args) {
			//DB작업에 필요한 객체변수 선언
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null; //쿼리문이 select일때 필요함
		
			//드라이버 로딩
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//DB에 접속(Connection 객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "student";
			String password = "java";
			
			//실제적으로 Oracle Driver가 사용되는 부분
			conn = DriverManager.getConnection(url,userId,password);
			
			//Statement객체 생성 -> Connection객체를 이용한다.
			stmt = conn.createStatement();
			Scanner scan = new Scanner(System.in);
			int id_max = 0;
			int id_min = 0;
			String sw = "";
			String sql = "";
			System.out.println("문제를 고르세요");
			sw = scan.nextLine();
			if(sw.equals("1")) {
				System.out.print("lprod_id값을 입력해주세요 : ");
				id_max = Integer.parseInt(scan.nextLine());
				sql = "select * from lprod where lprod_id >" + id_max; //실행할 sql문
			
			}
			else if(sw.equals("2")) {
				System.out.print("lprod_id min값을 입력해주세요 : ");
				id_min = Integer.parseInt(scan.nextLine());				
				System.out.print("lprod_id max값을 입력해주세요 : ");
				id_max = Integer.parseInt(scan.nextLine());
				sql = "select * from lprod where lprod_id >" + id_min + " and lprod_id < " + id_max; //실행할 sql문
			}			
			
			rs = stmt.executeQuery(sql);	 
			System.out.println("실행한 쿼리문 : " + sql);
			System.out.println("===쿼리문 실행 결과===");				
			
			
			while(rs.next()) {				
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("---------------------------------------------------");
			}
			
			System.out.println("출력 끝...");
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				if(rs!=null) try {rs.close();} catch(SQLException e) {}
				if(stmt!=null) try {stmt.close();} catch(SQLException e) {}
				if(conn!=null) try {conn.close();} catch(SQLException e) {}
				
		}
			
	}
		
		
}

	

