package controller;

import java.util.List;
import java.util.Scanner;

import service.BoardServicempl;
import service.IBoardService;
import vo.BoardVO;

public class BoardMain {

	private IBoardService service = BoardServicempl.getInstance();

	private Scanner scan = new Scanner(System.in);

	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 전체 게시글 보기");
		System.out.println("  2. 게시글 입력");
		System.out.println("  3. 게시글 삭제");
		System.out.println("  4. 게시글 수정");
		System.out.println("  5. 게시글 검색");
		System.out.println("  6. 종료");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1:
				displayMemberAll();
				break;
			case 2: 
				insertMember();
				break;
			case 3: 
				deleteMember();
				break;
			case 4: 
				updateMember();
				break;
			case 5:
				searchMember();
				break;
			case 6: 
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 5);
	}

	private void searchMember() {
		scan.nextLine(); // 입력버퍼 비우기
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");	
		
		System.out.print("게시글 제목>>");
		String title = scan.nextLine().trim();
		
		System.out.print("작성자 이름 >>");
		String name = scan.nextLine().trim();	
			
		
		System.out.print("게시글 내용>>");
		String content = scan.nextLine().trim();
		
		BoardVO mv = new BoardVO();	
		mv.setBoard_title(title);
		mv.setBoard_writer(name);
		mv.setBoard_content(content);
		
		// 입력한 정보로 검색한 내용을 출력하는 부분...
		
		
		System.out.println();
		System.out.println("------------------------------------------------");
		System.out.println("ID\t이름\t전화번호\t주소");
		System.out.println("------------------------------------------------");
		
		List<BoardVO> memList = service.searchMember(mv);
		
		for(BoardVO mv2 : memList) {
			System.out.println(mv2.getBoard_no() + "\t" 
								    + mv2.getBoard_title() + "\t" 
									+ mv2.getBoard_writer() + "\t" 
									+ mv2.getBoard_content() + "\t"
									+ mv2.getBoard_date());
			
		}
		
		System.out.println("----------------------------------------------------------------");
		System.out.println("출력 작업 끝...");
	}

	private void deleteMember() {
		System.out.println();
		System.out.print("삭제할 아이디를 입력하세요 >>");
		int num = Integer.parseInt(scan.next());
		num = service.deleteMember(num);

		if (num > 0) {
			System.out.println("회원 삭제 성공!");
		} else {
			System.out.println("회원 삭제 실패!!!");
		}
	}

	private void updateMember() {
		boolean check = false;
		int num;
		
		do {
			System.out.println();
			System.out.println("수정할 게시판 번호를 입력하세요.");
			System.out.println("게시판번호 >");
			num = Integer.parseInt(scan.next());
			check = service.getBoard(num);
			if (!check) {
				System.out.println("게시글이 존재하지 않습니다.");
				System.out.println("다시 입력하세요.");
			}
		} while (check == false);

		System.out.println("수정할 게시글 정보를 입력하세요.");
		System.out.println("새로운 게시글 제목 >");
		String title = scan.next();

		System.out.println("새로운  게시글 작성자 >>");
		String writer = scan.next();

		scan.nextLine();
		System.out.println("새로운 내용 >>");
		String content = scan.nextLine();

		BoardVO vo = new BoardVO();		
		vo.setBoard_no(num);
		vo.setBoard_title(title);
		vo.setBoard_writer(writer);
		vo.setBoard_content(content);
		num = service.updateMember(vo);
		if(num == 0) {
			System.out.println("수정실패");
		}
		else {
			System.out.println("수정 완료");
		}
	}

	private void displayMemberAll() {
		System.out.println();
		System.out.println("---------------------------------------------");
		System.out.println("게시글 번호\t 게시글 제목 \t 작성자 \t 작성일자 \t\t 내용");
		System.out.println("---------------------------------------------");
		List<BoardVO> list = service.displayMemberAll();
		if(list == null) {
			System.out.println("등록된 게시글이 없습니다.");
		}
		else {
			for (BoardVO mv : list) {
				System.out.println(mv.getBoard_no() + "\t" + mv.getBoard_title() + "\t" + mv.getBoard_writer() + "\t"
						+ mv.getBoard_date() + "\t" + mv.getBoard_content());
				System.out.println("---------------------------------------------");
			}
			
			System.out.println("출력 작업 끝");
		}
	}

	private void insertMember() {
		boolean check = false;
		int num;

		System.out.println("게시글 제목 >");
		String title = scan.next();
		scan.nextLine();
		System.out.println("게시글 작성자 >>");
		String writer = scan.next();
		scan.nextLine();		
		System.out.println("게시글 내용 >>");
		String content = scan.nextLine();
		BoardVO vo = new BoardVO();
		vo.setBoard_title(title);
		vo.setBoard_writer(writer);
		vo.setBoard_content(content);		

		int cnt = service.insertMember(vo);

		if (cnt > 0) {
			System.out.println("게시글 작성 성공");
		} else {
			System.out.println("게시글 작성 실패");
		}

	}

	public static void main(String[] args) {
		BoardMain memObj = new BoardMain();
		memObj.start();
	}
}
