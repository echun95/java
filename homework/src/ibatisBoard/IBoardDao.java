package ibatisBoard;

import java.util.List;

public interface IBoardDao {

	
	
	public List<BoardVO> searchMember(BoardVO vo);
	public int deleteMember(int num);
	public int updateMember(BoardVO vo);
	public List<BoardVO> displayMemberAll();
	public int insertMember(BoardVO vo);
	public boolean getBoard(int num);
	
}
