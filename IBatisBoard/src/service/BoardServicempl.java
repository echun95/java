package service;

import java.util.List;

import dao.BoardDaoImpl;
import dao.IBoardDao;
import vo.BoardVO;



public class BoardServicempl implements IBoardService {
	 private IBoardDao dao;
		
		private static BoardServicempl service;
		
		private BoardServicempl() {
			dao = BoardDaoImpl.getInstance();
		}
		
		public static BoardServicempl getInstance() {
			if(service == null) {
				service = new BoardServicempl();
			}
			return service;
		}
	@Override
	public List<BoardVO> searchMember(BoardVO vo) {
		
	  return dao.searchMember(vo);		
	}

	@Override
	public int deleteMember(int num) {
		
		return dao.deleteMember(num);
	}

	@Override
	public int updateMember(BoardVO vo) {
		return dao.updateMember(vo);
	}

	@Override
	public List<BoardVO> displayMemberAll() {
		return dao.displayMemberAll();
	}

	@Override
	public int insertMember(BoardVO vo) {
		return dao.insertMember(vo);
	}

	@Override
	public boolean getBoard(int num) {
		return dao.getBoard(num);
	}

}
