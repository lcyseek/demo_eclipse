package com.seek.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CollectionsDemo {

	private static ArrayList<String> arrayList = new ArrayList<>();
	private static ArrayList<Student> students = new ArrayList<>();
	private static String [] cities = {"深圳","广州","南京","北京","上海","武汉","长春","哈尔滨","杭州","厦门"};
	
	public static void main(String[] args) {
		init();
		
		//同步集合
//		List<String> list = Collections.synchronizedList(arrayList);
//		System.out.println(list.getClass().getName());
//		for(int i=0;i<10;i++){
//			list.add(""+i);
//		}
		
//		test1();
//		test2();
//		test3();
//		test4();
//		test5();
//		test6();
//		test7();
//		test8();
		test9();
	}

	private static void test9() {
		ArrayList<String> dest = new ArrayList<>();
		Collections.addAll(dest, cities);
		dest.add("dest");
		showData(dest);
	}

	private static void test8() {
		//强制转换为Student[]，则会报类型转换异常。不能将Object[] 转化为Student[].转化的话只能是取出每一个元素再转化，像这样：
//		Object[] objects = students.toArray();
//		
//		for(int i=0;i<objects.length;i++){
//			Student s = (Student) objects[i];
//			System.out.println(s);
//		}
		
		//根据参数数组的类型，构造了一个对应类型的，长度跟ArrayList的size一致的空数组
		Student[] ss = students.toArray(new Student[]{});
		for(int i=0;i<ss.length;i++){
			System.out.println(ss[i]);
		}
	}

	private static void test7() {

		Student tmp = students.get(3);
		Student s = new Student(tmp.id, tmp.name);
		System.out.println(s);
		//二分查找必须是针对有顺序的数组或集合。调用binarySearch之前， 需对列表进行升序排序sort(all)。如果没有对列表进行排序，则结果是不明确的。
		Collections.sort(students);
		showData(students);
		int index = Collections.binarySearch(students, tmp);
		System.out.println("查找到的index="+index);
	}

	private static void test6() {
		showData(students);
		//集合里最小的元素
		Student student = Collections.min(students);
//		Collections.max(students);
		System.out.println(student);
	}

	private static void init() {
		for(int i=0;i<10;i++){
			arrayList.add("a" + i);
			Student student = new Student(new Random().nextInt(40), "my name is "+i);
			students.add(student);
		}
	}

	/**
	 * 后来打印出des1.size()才知道des1的长度为0；3表示的是这个List的容纳能力为3，并不是说des1中就有了3个元素。
	 * 查看api才知道，它的capacity（容纳能力大小）可以指定（最好指定）。而初始化时size的大小永远默认为0，只有在进行add和remove等相关操作时，size的大小才变化。
	 * 然而进行copy()时候，首先做的是将desc1的size和src1的size大小进行比较，只有当desc1的size 大于或者等于src1的size时才进行拷贝，否则抛出IndexOutOfBoundsException异常。
	 */
	private static void test5() {
		// TODO Auto-generated method stub
	
		//这么写会报错!!!!!
//		ArrayList<String> dest = new ArrayList<>(arrayList.size());
		
		ArrayList<String> dest = new ArrayList<>(Arrays.asList(new String[arrayList.size()]));
		Collections.copy(dest, arrayList);
		
		showData(dest);
	}

	//替换所有的元素（Fill）
	private static void test4() {
		// TODO Auto-generated method stub
		Collections.fill(arrayList, "fill");
		showData(arrayList);
	}

	private static void test3() {
		showData(students);
	
		//按原顺序的反向顺序
		Collections.reverse(students);
		showData(students);
	}

	private static void test1() {
		String[] copy = new String[20];
		System.arraycopy(cities, 3, copy, 2, 5);
		
		//null null 北京 上海 武汉 长春 哈尔滨 null null null null null null null null null null null null null 
		for(int i=0;i<copy.length;i++){
			System.out.print(copy[i]+" ");
		}
		
		System.out.println("");
	}
	
	private static void test2() {
	
		//列表中的所有元素都必须实现 Comparable 接口
		Collections.sort(students);
		showData(students);
		
	}
	
	static void showData(ArrayList list){
		for(int i=0;i<list.size();i++){
			System.out.print(list.get(i)+" ");
		}
		
		System.out.println();
	}
	
	static class Student implements Comparable<Student>{
		int id;
		String name;
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
			return id - o.id;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		
		
		
	}
}
