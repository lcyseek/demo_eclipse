package com.seek.byteStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectOutputStreamDemo {
	
	private static final String ABS_NAME = "/Users/luchunyang/DemoSpace/eclipse/JavaIO/readme1.txt";
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		write();
		read();
	}
	
	private static void read() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ABS_NAME));
		Student student = (Student) ois.readObject();
		System.out.println(student);
		System.out.println(ois.readByte());
		System.out.println(ois.readBoolean());
		ois.close();
	}

	public static void write()throws IOException{
		//ObjectInputStream与ObjectOutputStream这两个类可以方便的实现对象的序列化(Serialize)和反序列化(Deserialize).
		//所读写的对象必须实现Serializable接口，对象中的transient和static类型成员变量不会被读取和写入
		
		FileOutputStream fos = new FileOutputStream(ABS_NAME);
		
		//对字节数组输出流对象的一个包装
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Student student = new Student("小李飞刀", 30);
		oos.writeObject(student);
		oos.writeByte(90);
		oos.writeBoolean(false);

		oos.close();
	}

	public static class Student implements Serializable{
		private static final long serialVersionUID = 1L;
		private String name;
		private int age;
		public Student(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
		@Override
		public String toString() {
			return "Student [name=" + name + ", age=" + age + "]";
		}
	}
}
