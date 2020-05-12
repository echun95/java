package ibatisBoard;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;





public class BoardDaoImpl implements IBoardDao{
	private SqlMapClient smc;
	private static IBoardDao dao;
	private Scanner scan = new Scanner(System.in);
	
	
	public BoardDaoImpl() {	
		Reader rd;
		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
		}catch(IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패!!!");
			e.printStackTrace();
		}
	}

	public static IBoardDao getInstance() {
		
		if(dao == null) {
			dao = new BoardDaoImpl();
		}
		return dao;
	}	
	

	@Override	
	public List<BoardVO> searchMember(BoardVO vo) {
		List<BoardVO> list = new  ArrayList<>();
		
		try {
			list = smc.queryForList("boardxml.getSearchMember", vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int deleteMember(int num) {
		int cnt = 0;
		try {
			cnt = smc.delete("boardxml.deleteMember", num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMember(BoardVO vo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("boardxml.updateMember", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<BoardVO> displayMemberAll() {
		List<BoardVO> memList = new ArrayList<BoardVO>();
		
		try {
			memList = smc.queryForList("boardxml.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public int insertMember(BoardVO boardvo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("boardxml.insertMember", boardvo);
			if(obj == null) {
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean getBoard(int num) {
boolean chk = false;
		
		try {
			BoardVO mv = 
			(BoardVO) 
			smc.queryForObject("boardxml.getMember", num);
			
			if(mv != null) {
				chk = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;	
	}

}
