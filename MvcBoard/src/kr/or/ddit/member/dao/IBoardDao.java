package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.member.vo.BoardVO;

public interface IBoardDao {

	
	
	public BoardVO searchMember(int num);
	public int deleteMember(int num);
	public int updateMember(BoardVO vo);
	public List<BoardVO> displayMemberAll();
	public int insertMember(BoardVO vo);
	public boolean getBoard(int num);
	
}
