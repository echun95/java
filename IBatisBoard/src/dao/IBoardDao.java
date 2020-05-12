package dao;

import java.util.List;

import vo.BoardVO;

public interface IBoardDao {

	
	
	public List<BoardVO> searchMember(BoardVO vo);
	public int deleteMember(int num);
	public int updateMember(BoardVO vo);
	public List<BoardVO> displayMemberAll();
	public int insertMember(BoardVO vo);
	public boolean getBoard(int num);
	
}
