package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberService {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB작업이 성공하면 1, 실패하면 0 이 반환됨.
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * 주어진 회원ID가 존재하는지 여부를 알아내는 메서드
	 * @param memId
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public boolean getMember(String memId);
	
	/**
	 * DB의 mymember테이블의 전체 레코드를 가져와  List에 담아서
	 * 반환하는 메서드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getAllMemberList();
	
	/**
	 * 하나의 MemberVO 자료를 이용하여 DB를 update하는 메서드
	 * @param mv update할 회원 정보가 들어있는 MemberVO객체
	 * @return 작업성공: 1, 작업실패: 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원ID
	 * @return 작업성공: 1, 작업실패: 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO에 담긴 자료를 이용하여 회원을 검색하는 메서드
	 * @param mv 검색할 자료가 들어있는 MemberVO 객체
	 * @return 검색된 결과를 담은 List
	 */
	public List<MemberVO> getSearchMember(MemberVO mv);
}
