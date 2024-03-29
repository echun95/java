package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class T16_NonSerializableParentTest {
/*
 * 부모클래스가 Serializable 인터페이스를 구현하고 있지 않을우
 * 부모 객체의 필드 값 처리 방법
 * 
 * 1. 부모가 Serializable 인터페이스를 구현하도록 해야한다.
 * 2. 자식 클래스에 writeObject()와 readObject()메서드를 이용하여 부모객체의 필드값을 처리할 수 있도록 직접 구현해야 한다.
 * 
 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
	
		FileOutputStream fos = new FileOutputStream("d:/D_Other/nonSerializeTest.bin");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Child child = new Child();
		child.setParentName("부모");
		child.setChildName("자식");
		oos.writeObject(child);//직렬화
		oos.flush();//생략가능
		oos.close();
		//fos.close(); 생력가능
		FileInputStream fis = new FileInputStream("d:/D_Other/nonSerializeTest.bin");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Child child2 = (Child)ois.readObject();
		System.out.println("parentName : " + child2.getParentName());
		System.out.println("childName : " + child2.getChildName());
		
		ois.close();
		fis.close();
		
		
		
		
	}
}
/**
 * 직렬화 될 때 자동으로 호출됨.
 * (접근 제한자가 private이 아니면 호출되지 않음.)
 * @author PC-04
 *
 */
//Serializable을 구현하지 않은 부모 클래스
class Parent{
	
	private String parentName;

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}

//Serializable을 구현한 자식클래스
class Child extends Parent implements Serializable{
	
	private String childName;

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeUTF(getParentName()); //부모객체 필드 값 처리
		out.defaultWriteObject();
	}
	/**
	 * 역 직렬확 될 때 자동으로 호출됨.
	 * (접근 저한자가 private이 아니면 자동호출이 되지않음)
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		setParentName(in.readUTF()); //부모객체 필드 값 처리
		in.defaultReadObject();
	}
}




