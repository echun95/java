package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/*	문제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
	     컴퓨터의 숫자는 난수를 이용하여 구한다.(스트라이크는 's' 볼은 'b'로 출력한다.)
	     컴퓨터의 난수가 957일때 실행 예시
	     숫자입력 --> 356 ->1s0b
	     
	    5번째 만에 맞추셨군요
*/



public class T11_BaseBallTest {

	public static void main(String[] args) {
	
	      HashSet<Integer> intRnd = new HashSet<>();
	      List<Integer> list = new ArrayList<>();
	      List<Integer> nan= new ArrayList<>();	      	      
	      boolean check = true;
	      int n = 0;
	      while(intRnd.size() < 3) {//Set의 데이터가 3개가 될때까지 반복한다.
	         int num = (int) (Math.random()  * (10));
	         boolean i = intRnd.add(num);
	         if(i) {
	        	 nan.add(num);
	         }
	      }
	      System.out.println(nan);
	      
	      
	      Scanner scan = new Scanner(System.in);
	      
	      do {
	    	  
	    	  int strike = 0;
	    	  int ball = 0;
	    	  int out = 0;
	    	  list.clear();
	    	  
	      System.out.println("숫자를 입력해주세요");
	      int a = Integer.parseInt(scan.nextLine());
	      int num100 = a/100;
	      int num10 = (a-(num100*100))/10;
	      int num1 = a%10;
	      
	      list.add(num100);
	      list.add(num10);
	      list.add(num1);
	      
	      for (int i = 0; i < nan.size(); i++) {
	    	for(int j = 0; j< nan.size(); j++) {
	    		if(nan.get(i) == list.get(j)) {
	    			if(i==j) {
	    				strike += 1;
	    				break;
	    			}
	    			else {
	    				ball += 1;
	    				break;
	    			}
	    		}	    		
	    	}	    	  
		}
	      out = nan.size() - strike - ball;
	      
	      System.out.println("strike =  " + strike + " ball =  "+ ball + " out =  " + out);
	      n++;
	      if(strike == nan.size()) {
	    	  System.out.println("정답입니다." + n + "번 만에 맞추셨습니다.");
	    	  check = false;
	      }
	      else {
	    	  System.out.println("틀리셨습니다.");
	      }
	 }while(check);	      
	      
	}

}
