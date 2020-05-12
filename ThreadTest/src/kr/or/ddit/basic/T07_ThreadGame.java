package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 * 사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초안에 입력이 없으면 진 것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과 예시 ) 
 * ====결과====
 * 컴퓨터 : 가위
 * 당   신 : 바위
 * 결   과 : 당신이 이겼습니다.
 * 
 */
public class T07_ThreadGame {
	public static boolean inputCheck = false;
	public static String myinput = new String();
	
	   public static void main(String[] args) {
	      Thread th1 = new Input();
	      Thread th2 = new Count();
	      
	      th1.start();
	      th2.start();      
	      
	      
	   }
	
}

class Input extends Thread{

	   @Override
	   public void run() {
		   String[] str = new String[3];
		   str[0] = "가위";
		   str[1] = "바위";
		   str[2] = "보";
		   
		  String a = str[(int)(Math.random()*3)];
		  T07_ThreadGame.myinput = JOptionPane.showInputDialog("가위, 바위, 보 중 아무거나 입력하세요.");
		  
	      System.out.println("컴퓨터 : " + a);  
	      System.out.println("당   신 : " + T07_ThreadGame.myinput);  
	      if(T07_ThreadGame.myinput.equals(a)) {
	    	System.out.println("비기셨습니다.");  
	      }
	      else if(T07_ThreadGame.myinput.equals("가위") && a.equals("보") || T07_ThreadGame.myinput.equals("바위") && a.equals("가위")
	    		  ||T07_ThreadGame.myinput.equals("보") && a.equals("바위")){
	    			  System.out.println("당신이 이기셨습니다.");
	      }
	      else {
	    	  System.out.println("당신이 지셨습니다.");
	      }
	      //입력이 완료되면 inputCheck변수를 true로 변경한다.
	      T07_ThreadGame.inputCheck =true;
	      
	   }
	   
	}
/*
 * 카운트 다운을 처리하는 쓰레드 클래스
 */

class Count extends Thread{

   @Override
   public void run() {
      for(int i= 5; i>=0; i--) {
         //입력이 완료되었는지 여부를 검사하고 입력이 완료되면
         //run()메서드를 종료시킨다. 즉, 현재 쓰레드를 종료 시킨다.
         if(T07_ThreadGame.inputCheck) {
            return;//run()메서드가 종료되면 쓰레드도 끝난다.
         }
         System.out.println(i);
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      //10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
      System.out.println("입력시간이 지났습니다. 당신이 졌습니다.");
      System.exit(0);//프로그램을 종료시키는 명령
   }
   
}