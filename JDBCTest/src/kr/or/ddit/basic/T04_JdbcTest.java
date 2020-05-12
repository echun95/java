package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
 * LPROD 테이블에 새로운 데이터를 추가하기
 * lprod_gu와 lprod_nm은 직접 입력받아 처리하고 lprod_id는 혀재의 lprod_id들 중 제일 큰 값보다 1증가된 값으로 한다.
 * (기타사항 : lprod_gu도 중복되는지 검사한다.)
 */
public class T04_JdbcTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		String gu = "";
		String nm = "";
		ResultSet rs;
		int max = 0;
		int count = 0;
		try {
			

			conn = DBUtil.getConnection();
			System.out.println("lprod_gu를 입력해주세요");
			gu = scan.nextLine();
			System.out.println("lprod_nm을 입력해주세요");
			nm = scan.nextLine();

			String sql = "select count(*) cnt from lprod where lprod_gu = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gu);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("cnt");
			}
			if (count > 0) {
				System.out.println("lprod_gu가 중복되었습니다.");
				return;
			}

			sql = "select max(lprod_id) from lprod";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			if (rs.next()) {
				max = rs.getInt("max(lprod_id)");
			}
			sql = "insert into lprod values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, max + 1);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);

			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "건의 데이터가 추가되었습니다.");

		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 사용했던 자원 반납
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

	}
}
