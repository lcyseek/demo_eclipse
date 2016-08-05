package com.seek.hal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.junit.Test;

public class Person {

	private int age;
	private String name;
	
	public Person(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	
	public int div(int a,int b){
		return a/b;
	}
	
	@Test
	public void showFrame(){
		JFrame jFrame = new JFrame();
		JPanel jpPanel = new JPanel();
		JTextArea jTextArea = new JTextArea();
		jTextArea.setText("Hello android");
		jFrame.add(jTextArea);
		jFrame.setSize(400, 400);
		jFrame.show();
	}
	
	public void useTime(){
		for(;;){
			
		}
	}
	
	public int cal(int a,int b){
		System.out.println("a="+a+" , b="+b);
		return a+b;
	}
	
}
