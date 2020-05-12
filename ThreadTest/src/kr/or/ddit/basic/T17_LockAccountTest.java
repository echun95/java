package kr.or.ddit.basic;

import java.util.concurrent.locks.ReentrantLock;

/*
 * 
 */
public class T17_LockAccountTest {
	public static void main(String[] args) {
		LockAccount IAcc = new LockAccount();
		IAcc.setBalance(10000); //입금처리
	
		
		BankThread2 bth1 = new BankThread2(IAcc);
		BankThread2 bth2 = new BankThread2(IAcc);
		
		bth1.start();
		bth2.start();
		
		
	}
}


//입출금을 담당하는 클래스
class LockAccount{
	private int balance; //잔액이 저장될 변수
	
	//lock객체 생성 -> 되도록이면 private final로 만든다.
	private final ReentrantLock lock = new ReentrantLock();

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	//입금하는 메서드
	public void deposit(int money) {
		//Lock객체의 lock()메서드가 동기화 시작이고, unlock()메서드가 동기화의 끝을 나타낸다.
		// lock(메서드로 동기화를 설정한 곳에서는 반드시 unlock()메서드로 해제해야한다.
		lock.lock();//시작
		balance += money;	//동기화 처리 부분
		lock.unlock(); //해제
	}
	
	//출금하는 메서드(출금 성공 : true, 출금 실패 : false를 반환)
	public boolean withdraw(int money) {
		lock.lock();
		boolean chk = false;
		//try~catch 블럭을 사용할 경우에는 unlock()메서드 호출은 finally블럭에서 하도록 한다.
		try {
			if(balance >= money) {
				for(int i = 1; i<= 1000000000; i++) {} //시간때우기용
				balance -= money;
				System.out.println("메서드 안에서 balance = " + getBalance());
				chk = true;
			}
		} catch (Exception e) {
			chk = false;
		}finally {
			lock.unlock();
		}
		return chk;
	}
}

class BankThread2 extends Thread{
	private LockAccount IAcc;
	public BankThread2(LockAccount IAcc) {
		this.IAcc= IAcc;
	}
	@Override
	public void run() {
		boolean result = IAcc.withdraw(6000);
		System.out.println("쓰레드 안에서 result = " + result + ", balance = " + IAcc.getBalance());
	}
}