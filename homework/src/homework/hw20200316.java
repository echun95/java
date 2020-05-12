package homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class hw20200316 {
  /*문제) 학번, 이름, 국어점수, 수학점수, 영어점수, 총점, 등수를 멤버변수로 갖는 Student클래스를 만든다.
   생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다
   이 Student객체들은 List에 저장하여 관리한다.
   List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의 역순으로 정렬하는 부분을 프로그램하시오.
   (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
   (학번의 정렬기준은 Student클래스 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
   */
   
   public static void main(String[] args) {
      
      List<Student> memlist = new ArrayList<>();
      memlist.add(new Student("1","홍길동",0,50,50));
      memlist.add(new Student("5","변학도",50,70,15));
      memlist.add(new Student("7","성춘향",40,30,30));
      memlist.add(new Student("3","이순신",10,50,41));
      memlist.add(new Student("9","강감찬",10,50,40));
      memlist.add(new Student("6","일지매",25,64,23));
      
      for(Student mem : memlist) {
         mem.setSum(mem.getEng() + mem.getMath() + mem.getKor());
         mem.setRank(1);
      }
      
      for (int i = 0; i < memlist.size(); i++) {
         for(int j = 0; j < memlist.size(); j++) {
            if(memlist.get(i).getSum() < memlist.get(j).getSum()) {
               memlist.get(i).setRank(memlist.get(i).getRank()+1);
            }
         }      
      }

      System.out.println("정렬 전 : ");
      for(Student mem : memlist) {
         System.out.println(mem);
      }
      
      Collections.sort(memlist);   
      System.out.println("학번 오름차순 정렬 후 : ");
      for(Student mem : memlist) {
         System.out.println(mem);
      }
      
      Collections.sort(memlist, new SortSumDesc());   
      System.out.println("점수 내림차순 정렬 후 : ");
      for(Student mem : memlist) {
         System.out.println(mem);
      }
      
      Collections.sort(memlist, new SortSumDesc());   
      System.out.println("최종 정렬 후 : ");
      for(int i = 0; i<memlist.size()-1; i++) {         
         if(memlist.get(i).getSum() == memlist.get(i+1).getSum()) {              
             
            if(Integer.parseInt(memlist.get(i).getNum()) < 
              Integer.parseInt(memlist.get(i+1).getNum())) {
               Collections.swap(memlist, i, i+1);
               for(int j = i+1; j < memlist.size(); j++) {
                 if(memlist.get(i).getSum() == memlist.get(j).getSum()) {                    
                    if(Integer.parseInt(memlist.get(i).getNum()) < 
                          Integer.parseInt(memlist.get(j).getNum())) {
                        Collections.swap(memlist, i, j);
                    }
                 }
               }
            }             
         }
      }
      for(Student mem : memlist) {
         System.out.println(mem);
      }
      
      
   }
}
class Student implements Comparable<Student>{
   private String num;
   private String name;
   private int kor;
   private int math;
   private int eng;
   private int sum;
   private int rank;
   
   public Student(String num, String name, int kor, int math, int eng) {
      super();
      this.num = num;
      this.name = name;
      this.kor = kor;
      this.math = math;
      this.eng = eng;
   }
   public String getNum() {
      return num;
   }
   public void setNum(String num) {
      this.num = num;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public int getKor() {
      return kor;
   }
   public void setKor(int kor) {
      this.kor = kor;
   }
   public int getMath() {
      return math;
   }
   public void setMath(int math) {
      this.math = math;
   }
   public int getEng() {
      return eng;
   }
   public void setEng(int eng) {
      this.eng = eng;
   }
   public int getSum() {
      return sum;
   }
   public void setSum(int sum) {
      this.sum = sum;
   }
   public int getRank() {
      return rank;
   }
   public void setRank(int rank) {
      this.rank = rank;
   }
   
   @Override
   public String toString() {
      return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", math=" + math + ", eng=" + eng + ", sum="
            + sum + ", rank=" + rank + "]";
   }
   @Override
   public int compareTo(Student student) {
      return getNum().compareTo(student.getNum());
   }   
}


class SortSumDesc implements Comparator<Student>{

   @Override
   public int compare(Student st1, Student st2) {
      return new Integer(st1.getSum()).compareTo(st2.getSum()) * -1;
   }
   
}