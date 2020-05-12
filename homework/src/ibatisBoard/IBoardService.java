package ibatisBoard;

import java.util.List;

public interface IBoardService {

	
	
	
	public int deleteMember(int num);
	public int updateMember(BoardVO vo);
	public List<BoardVO> displayMemberAll();
	public int insertMember(BoardVO vo);
	public boolean getBoard(int num);
	public List<BoardVO> searchMember(BoardVO vo);
}
