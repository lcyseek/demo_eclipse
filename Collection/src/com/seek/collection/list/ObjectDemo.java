package com.seek.collection.list;

import java.util.Iterator;
import java.util.LinkedList;

public class ObjectDemo {

	public static void main(String[] args) {
		LinkedList<Student> linkedList = new LinkedList<>();
		
		Student jim = new Student(11, "jim", "jim@gmail.com");
		Student tom = new Student(21, "tom", "tom@gmail.com");
		Student kity = new Student(10, "kity", "kitiy@gmail.com");
		Student james = new Student(10, "james", "james@gmail.com");
		Student tbag = new Student(10, "t_bag", "t_bag@gmail.com");
		Student linken = new Student(10, "linken", "linken@gmail.com");
		
		linkedList.add(jim);
		linkedList.add(tom);
		linkedList.add(kity);
		linkedList.add(james);
		linkedList.add(tbag);
		linkedList.add(linken);
		
		showData(linkedList);
		
		
		Student linken1 = new Student(10, "linken", "linken@gmail.com");
		System.out.println(linkedList.contains(linken1));//会调用equals方法来比较对象,默认的equals是直接比较是否是同一个对象
		@SuppressWarnings("unchecked")
		LinkedList<Student> cloneList = (LinkedList<Student>) linkedList.clone();
		cloneList.remove(james);
		showData(linkedList);
		
	}

	private static void showData(LinkedList<Student> linkedList) {
		Iterator<Student> iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			Student student = (Student) iterator.next();
			System.out.println(student);
		}
	}

}

class Student{
	
	private int age;
	private String name;
	private String email;
	
	public Student(int age, String name, String email) {
		super();
		this.age = age;
		this.name = name;
		this.email = email;
	}

	
	@Override
	public String toString() {
		return "Student [age=" + age + ", name=" + name + ", email=" + email + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (age != other.age)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
