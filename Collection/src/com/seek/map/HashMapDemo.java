package com.seek.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 无序!!! key可以为null
 * @author lcy
 *
 */

public class HashMapDemo {

	public static void main(String[] args) {
		HashMap<String, Student> map = new HashMap<>();
		
		Student jim = new Student(11, "jim", "jim@gmail.com");
		Student tom = new Student(21, "tom", "tom@gmail.com");
		Student kity = new Student(10, "kity", "kitiy@gmail.com");
		Student james = new Student(10, "james", "james@gmail.com");
		Student tbag = new Student(10, "t_bag", "t_bag@gmail.com");
		Student linken = new Student(10, "linken", "linken@gmail.com");
		Student linken1 = new Student(100, "linken1", "linken1@gmail.com");
		
		map.put("jim", jim);
		map.put("tom", tom);
		map.put("kity", kity);
		map.put("james", james);
		map.put("tbag", tbag);
		map.put("linken", linken);
		map.put("linken", linken1);//会替换已经存在的
		
		//这种方法遍历效率高
		showDataEntry(map);
		
		Student james_copy = new Student(10, "james", "james@gmail.com");
		System.out.println("containsKey tom :"+map.containsKey("tom"));
		System.out.println("containsValue james:"+map.containsValue(james_copy));
		
		System.out.println("remove tom :"+map.remove("tom"));//返回被移除的value
		showDataKey(map);
		showDataValue(map);
	}

	private static void showDataKey(HashMap<String, Student> map) {
		System.out.println("\n");
		Set<String> keys = map.keySet();
		for (String key : keys) {
			System.out.println(key+"="+map.get(key));
		}
	}
	
	private static void showDataValue(HashMap<String, Student> map) {
		Collection<Student> collection = map.values();
		Iterator<Student> iterator = collection.iterator();
		while (iterator.hasNext()) {
			Student student = (Student) iterator.next();
			System.out.println(student);
		}
	}
	
	//这种方法遍历效率高
	private static void showDataEntry(HashMap<String, Student> map) {
		Set<Map.Entry<String, Student>> entrys = map.entrySet();
		for(Map.Entry<String, Student> entry:entrys){
			System.out.println(entry.getKey()+"="+entry.getValue());
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
