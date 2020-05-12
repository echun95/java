package homework;
public class MemberVO {
	private String memberId;
	private String memberName;
	private int memberTel;
	private String memberAddr;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(int memberTel) {
		this.memberTel = memberTel;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	
	public MemberVO(String memberId, String memberName, int memberTel, String memberAddr) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberTel = memberTel;
		this.memberAddr = memberAddr;
	}
	
	
}
