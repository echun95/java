package homework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class hw20200318 {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int choice = 0;
		int money, divide, num;

		do {
			money = 0;
			divide = 0;
			num = 0;
			System.out.println("1. 로또 구입");
			System.out.println("2. 프로그램 종료");
			System.out.print("메뉴선택 : ");
			choice = Integer.parseInt(s.nextLine());

			switch (choice) {
			case 1:
				Set<Integer> set = new HashSet<>();
				Set<Integer> answer = new HashSet<>();

				System.out.println("로또 구입");
				System.out.print("금액 입력 : ");
				money = Integer.parseInt(s.nextLine());
				
				divide = money - (1000 * (money / 1000));
				System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + divide + "원입니다.");
				System.out.println("로또는 " + money/1000 + "장 사셨습니다.");
				
				while (answer.size() < 6) {
					answer.add((int) (Math.random() * 45 + 1));
				}
				System.out.println("로또 당첨번호 : " + answer);

				for (int i = 0; i < money / 1000; i++) {
					while (set.size() < 6) {
						num = (int) (Math.random() * 10 + 1);
						set.add(num);
					}
					System.out.println("로또번호 " + (i + 1) + set);					
					
					ArrayList<Integer> setList = new ArrayList<>(set);
					ArrayList<Integer> answerList = new ArrayList<>(answer);
					int count = 0;

					for (int k = 0; k < setList.size(); k++) {
						for (int j = 0; j < answerList.size(); j++) {
							if (setList.get(k) == answerList.get(j)) {
								count++;
							}
						}
					}

					switch (count) {
					case 6:
						System.out.println("1등");
						break;
					case 5:
						System.out.println("2등");
						break;
					case 4:
						System.out.println("3등");
						break;
					case 3:
						System.out.println("4등");
						break;
					case 2:
					case 1:
					case 0:
						System.out.println("꽝");
						break;
					}
					set.clear();
				}
				break;
			case 2 :
				break;
			}

		} while (!(choice == 2));
	}	
}