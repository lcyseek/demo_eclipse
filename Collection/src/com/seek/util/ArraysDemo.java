package com.seek.util;

import java.util.Arrays;
import java.util.List;

public class ArraysDemo {
	private static String [] cities = {"深圳","广州","南京","北京","上海","武汉","长春","哈尔滨","杭州","厦门"};
	private static int [] ids = {12,2,86,13,22,24,1,99,47,66,87,3,44,4,74,69,25};
	private static Student[] students ;
	
	public static void main(String[] args) {
//		Arrays.sort(ids);
//		printIDS();
		
//		students = new Student[]{new Student(11, "asdf"),new Student(2, "eeeee"),new Student(77, "-----")};
//		Arrays.sort(students);
//		printStudents();
		
//		Arrays.fill(cities, "hello");
//		printCities();
		
//		Arrays.sort(ids);
//		int index = Arrays.binarySearch(ids, 3);
//		System.out.println("index = "+index);
		
		//copyOf()的第二个自变量指定要建立的新数组长度，如果新数组的长度超过原数组的长度，则保留数组默认值，例如：
//		String[] newCities = Arrays.copyOf(cities, 20);
//		for(int i=0;i<newCities.length;i++){
//			System.out.print(newCities[i]+" ");
//		}
//		System.out.println();
		
		//将一个数组转化为一个List对象，这个方法会返回一个ArrayList类型的对象， 这个ArrayList类并非java.util.ArrayList类，而是Arrays类的静态内部类！用这个对象对列表进行添加删除更新操作，就会报UnsupportedOperationException异常
//		List<String> list = Arrays.asList("hello","world","java","mememee","1111111","dddddddd","dddssssss");
//		Arrays.asList(cities);
//		list.add("tttt");//会报错!!
		
	}
	
	private static void  printIDS() {
		for(int i=0;i<ids.length;i++){
			System.out.print(ids[i]+" ");
		}
		System.out.println("");
	}
	
	private static void  printCities() {
		for(int i=0;i<cities.length;i++){
			System.out.print(cities[i]+" ");
		}
		System.out.println("");
	}
	
	private static void printStudents(){
		for(int i=0;i<students.length;i++){
			System.out.print(students[i]+" ");
		}
		System.out.println("");
	}
	
	static class Student implements Comparable<Student>{
		private int id;
		private String name;
		@Override
		public String toString() {
			return "Student [id=" + id + ", name=" + name + "]";
		}
		public Student(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		@Override
		public int compareTo(Student o) {
			// TODO Auto-generated method stub
			return this.id - o.id;
		}
		
	}

}
