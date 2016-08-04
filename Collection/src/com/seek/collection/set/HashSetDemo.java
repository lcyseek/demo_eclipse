package com.seek.collection.set;

import java.util.HashSet;
import java.util.Iterator;


/**
 * 机制:HashMap的基础上来实现的.HashSet的存储方式是把HashMap中的Key作为Set的对应存储项.是专门为快速查询而设计的
 * 不能重复,允许null,插入顺序和取出顺序不一致
 * 线程不安全
 *
 * 
 * HashSet怎么保证数据不重复:equals和hashCode方法
 * equals方法和hashCode方法必须兼容 如：equals方法判断的是用户的名字name，那么hashCode的返回的hashcode必须是name。hashcode()；
 * 
 * 在java的集合中，判断两个对象是否相等的规则是:
 * 1.判断两个对象的hashCode是否相等
 * 		如果不相等，认为两个对象也不相等，完毕 ;如果相等，转入2
 * 2.判断两个对象用equals运算是否相等
 * 		如果不相等，认为两个对象也不相等
 * 		如果相等，认为两个对象相等
 */

public class HashSetDemo {

	public static void main(String[] args) {
		HashSet<String> set = new HashSet<>();
		set.add("hello");
		set.add("android");
		set.add("tom");
		set.add("jim");
		set.add("kity");
		set.add("james");
		set.add(null);
		showData(set);
		
		Student jim = new Student(11, "jim", "jim@gmail.com");
		Student tom = new Student(21, "tom", "tom@gmail.com");
		Student kity = new Student(10, "kity", "kitiy@gmail.com");
		Student james = new Student(10, "james", "james@gmail.com");
		Student tbag = new Student(10, "t_bag", "t_bag@gmail.com");
		Student linken = new Student(10, "linken", "linken@gmail.com");
		Student linken1 = new Student(10, "linken", "linken@gmail.com");

		HashSet<Student> students = new HashSet<>();
		students.add(jim);
		students.add(tom);
		students.add(kity);
		students.add(james);
		students.add(tbag);
		students.add(linken);
		students.add(linken1);
		
		Iterator<Student> iterator = students.iterator();
		while (iterator.hasNext()) {
			Student student = (Student) iterator.next();
			System.out.println(student);
		}
	}

	private static void showData(HashSet<String> set) {
		// set不提供给予数据下标的访问
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.print(string+" ");
		}
		
		System.out.println("\n");
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
