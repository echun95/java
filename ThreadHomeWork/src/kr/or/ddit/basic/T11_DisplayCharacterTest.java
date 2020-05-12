package kr.or.ddit.basic;
public class T11_DisplayCharacterTest {
	static String strRank = "";
	static String[] result = new String[10];
	static int rank = 0;
	public static void main(String[] args) {
		DisplayCharacter[] disChars = new DisplayCharacter[] {
				new DisplayCharacter(" 1번 말"),
				new DisplayCharacter(" 2번 말"),
				new DisplayCharacter(" 3번 말"),
				new DisplayCharacter(" 4번 말"),
				new DisplayCharacter(" 5번 말"),
				new DisplayCharacter(" 6번 말"),
				new DisplayCharacter(" 7번 말"),
				new DisplayCharacter(" 8번 말"),
				new DisplayCharacter(" 9번 말"),
				new DisplayCharacter("10번 말")};

		for (int i = 0; i < disChars.length; i++) {
			disChars[i].start();
		}

		for (DisplayCharacter dc : disChars) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("경기 끝...");
		System.out.println("---------------------------------");
		System.out.println();
		System.out.println("경기 결과");
		System.out.println("순위 : ");
		System.out.println(strRank);
	}
}

// 영어 대문자를 출력하는 쓰레드 클래스
class DisplayCharacter extends Thread {
	private String name;

	public DisplayCharacter(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		int i  = 50;
		int j = 0;
		char[] str = new char[] {'>','-','-','-','-','-','-','-','-','-',
				'-','-','-','-','-','-','-','-','-','-',
				'-','-','-','-','-','-','-','-','-','-',
				'-','-','-','-','-','-','-','-','-','-',
				'-','-','-','-','-','-','-','-','-','-',};
		
		for (i = 0; i<49; i++) {
			char a = str[i];
			char b = str[i+1];
			char tmp = ' ';
			tmp = a;
			str[i] = b;
			str[i+1] = tmp;
			clear();
			T11_DisplayCharacterTest.result
			[Integer.parseInt(name.substring(0,2).trim())-1] = String.valueOf(str);
			print();
			try {
				// sleep()메서드의 값을 200~500사이의 난수로 한다.
				Thread.sleep((int) (Math.random() * 301 + 200));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println();
		T11_DisplayCharacterTest.rank++;
		System.out.println(name + "출력 끝...");
		T11_DisplayCharacterTest.strRank +=T11_DisplayCharacterTest.rank + "등 " + name + "\n";
	}
	
	public void clear() {
		for (int i = 0; i < 100; i++) {
			System.out.println(" ");
		}
	}
	public void print() {
		System.out.println("1번말 \t" + T11_DisplayCharacterTest.result[0]);
		System.out.println("2번말 \t" + T11_DisplayCharacterTest.result[1]);
		System.out.println("3번말 \t" + T11_DisplayCharacterTest.result[2]);
		System.out.println("4번말 \t" + T11_DisplayCharacterTest.result[3]);
		System.out.println("5번말 \t" + T11_DisplayCharacterTest.result[4]);
		System.out.println("6번말 \t" + T11_DisplayCharacterTest.result[5]);
		System.out.println("7번말 \t" + T11_DisplayCharacterTest.result[6]);
		System.out.println("8번말 \t" + T11_DisplayCharacterTest.result[7]);
		System.out.println("9번말 \t" + T11_DisplayCharacterTest.result[8]);
		System.out.println("10번말 \t" + T11_DisplayCharacterTest.result[9]);
	}
	
	
}
