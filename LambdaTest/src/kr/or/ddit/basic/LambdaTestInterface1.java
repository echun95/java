package kr.or.ddit.basic;

/*
 * 함수적 인터페이스 -> 추상메서드가 1개가 선언된 인터페이스
 */
public interface LambdaTestInterface1 {
	//반환값이 없고 매개변수도 없는 추상메서드 선언
	public void test();
}
interface LambdaTestInterface2{
	//반환값이 없고 배개변수가 있는 추상메서드 선언
	public void test(int a);
}
interface LambdaTestInterface3{
	//반환값이 있고 배개변수가 있는 추상메서드 선언
	public int test(int a, int b);
}