package mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class BoardDaoImpl implements IBoardDao{
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Scanner scan = new Scanner(System.in);
	private static IBoardDao dao;
	
	
	public static IBoardDao getInstance() {
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		
		return dao;
	}
	
	
	private void disConnect() {
		if (stmt != null) {try {stmt.close();} catch (SQLException e){}}
		if (pstmt != null) {try {pstmt.close();} catch (SQLException e){}}
		if (conn != null) {try {conn.close();} catch (SQLException e){}}		
	}
	
	
	@Override	
	public BoardVO searchMember(int num) {
		BoardVO boardvo = new BoardVO();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board where board_no = " + num;
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				boardvo.setBoard_no(rs.getInt("board_no"));
				boardvo.setBoard_title(rs.getString("board_title"));
				boardvo.setBoard_writer(rs.getString("board_writer"));
				boardvo.setBoard_date(rs.getDate("board_date"));
				boardvo.setBoard_content(rs.getString("board_content"));
			}
			System.out.println("출력 작업 끝..");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardvo;
	}

	@Override
	public int deleteMember(int num) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,num);
		    cnt = pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return cnt;
	}

	@Override
	public int updateMember(BoardVO vo) {
		int cnt = 0;		
		try {
			conn = DBUtil.getConnection();
			String sql = "update jdbc_board set board_title = ?, board_writer = ?,"
					+ " board_date = sysdate, board_content = ? where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBoard_title());
			pstmt.setString(2, vo.getBoard_writer());
			pstmt.setString(3, vo.getBoard_content());
			pstmt.setInt(4, vo.getBoard_no());
			
			cnt = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> displayMemberAll() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn =DBUtil.getConnection();
			String sql = "select * from jdbc_board";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
			BoardVO boardvo = new BoardVO();
			
			boardvo.setBoard_no(rs.getInt("board_no"));
			boardvo.setBoard_title(rs.getString("board_title"));
			boardvo.setBoard_writer(rs.getString("board_writer"));
			boardvo.setBoard_date(rs.getDate("board_date"));
			boardvo.setBoard_content(rs.getString("board_content"));
			list.add(boardvo);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertMember(BoardVO boardvo) {
		int cnt = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into jdbc_board values(board_seq.nextVal,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardvo.getBoard_title());
			pstmt.setString(2, boardvo.getBoard_writer());			
			pstmt.setString(3, boardvo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}		
		return cnt;
	}

	@Override
	public boolean getBoard(int num) {
		boolean check = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) as cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if(cnt > 0 ) {
				check = true;
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return check;		
	}

}
