package com.seek.collection.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ArrayListAndLinkedListDemo {
	private static ArrayList<String> arrayList = new ArrayList<>();
	private static LinkedList<String> linkedList = new LinkedList<>();
	private static int[] randomIndex = new int[200];
	private static int NUM = 6360000;
	
	public static void main(String[] args) {
		init();
		
		//尾部顺序添加数据 一般来说是ArrayList效率比较高。
		//但是这个结果很大不确定性！！！！，数组每次达到容量时，都会非配原来容量的一半。接下来的插入到尾部就很快了
		//链表每次都需要新分配一个节点的内存来存储元素。添加到尾部并不需要从头遍历链表，因为它内部已经保存了尾部元素的引用。直接添加到尾部就可以了

		array_tail_add();
		list_tail_add();
		
		//读取ArrayList块。LinkedList要查找index所在的位置，注意它的查找优化了一下，判断index和 size/2的大小，决定从头查找还是从尾部查找
//		array_get();
//		list_get();
		
		//随即删除还是ArrayList快
		/**
		 * 删除尾部时，array和link差不多，都很快，是因为array不用移动元素，link可以直接定位到尾部，然后删除。
		 * 删除中部时，array比较慢，link非常慢，array每次删除需要移动一半的元素，link定位耗时相当大（每次都要重新定位到中部），由此可见，数据量较大时，定位的开销比移动元素的开销大的多。
		 * 删除首部时，array较慢，link很快，array每次删除都要移动所有的元素，而link可以直接定位到首部，直接删除。
		 */
		//按照下表删除  这对于LinkedList来说不公平。按照对象来删除才叫公平
		array_delete_byIndex();
		list_delete_byIndex();
		
		
//		array_delete_byObject 耗时:5312
//		list_delete_byObject 耗时:8754
//		array_delete_byObject();
//		list_delete_byObject();
		
//		array_insert();
//		list_insert();
	}


	private static void list_insert() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		for(int i=0;i<100000;i++){
			linkedList.add(new Random().nextInt(100000), "inset -> " + i);
		}
		long end = System.currentTimeMillis();

		System.out.println("list_insert 耗时:"+(end - start));
	}


	private static void array_insert() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		for(int i=0;i<randomIndex.length;i++){
			arrayList.add(new Random().nextInt(100000), "inset -> " + i);
		}
		long end = System.currentTimeMillis();

		System.out.println("array_insert 耗时:"+(end - start));
	}


	private static void init() {
		for(int i=0;i<randomIndex.length;i++){
			randomIndex[i] = (int)(Math.random()*(NUM));
		}
	}

	/************************************** delete *********************************************/
	
	private static void list_delete_byIndex() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		for(int i=0;i<randomIndex.length;i++){
			linkedList.remove(randomIndex[i]);
		}
		long end = System.currentTimeMillis();
		System.out.println("list_delete 耗时:"+(end - start));
	}

	private static void array_delete_byIndex() {
		// TODO Auto-generated method stub
		long start = System.currentTimeMillis();
		for(int i=0;i<randomIndex.length;i++){
			arrayList.remove(randomIndex[i]);
		}
		long end = System.currentTimeMillis();

		System.out.println("array_delete 耗时:"+(end - start));
	}


	private static void list_delete_byObject() {
		long start = System.currentTimeMillis();
		for(int i=0;i<randomIndex.length;i++){
			linkedList.remove("hello java ---> " + randomIndex[i]);
		}
		long end = System.currentTimeMillis();

		System.out.println("list_delete_byObject 耗时:"+(end - start));
	}

	private static void array_delete_byObject() {
		long start = System.currentTimeMillis();
		for(int i=0;i<randomIndex.length;i++){
			arrayList.remove("hello java ---> " + randomIndex[i]);
		}
		long end = System.currentTimeMillis();

		System.out.println("array_delete_byObject 耗时:"+(end - start));
		
	}
	
	/************************************** get *********************************************/
	
	private static void list_get() {
		long start = System.currentTimeMillis();
		
		for(int i=0;i<randomIndex.length;i++){
			linkedList.get(randomIndex[i]);
		}
		long end = System.currentTimeMillis();
		System.out.println("list_get 耗时:"+(end - start));

	}

	private static void array_get() {
		long start = System.currentTimeMillis();
		for(int i=0;i<randomIndex.length;i++){
			arrayList.get(randomIndex[i]);
		}
		long end = System.currentTimeMillis();
		System.out.println("array_get 耗时:"+(end - start));
	}

	/************************************** add *********************************************/
	
	private static void list_tail_add() {
		long start = System.currentTimeMillis();
		//每次都是末尾添加数据
		for(int i=0;i<NUM;i++){
			linkedList.add("hello java ---> " + i);
		}
		long end = System.currentTimeMillis();
		System.out.println("list_tail_add 耗时:"+(end - start));
	}

	private static void array_tail_add() {
		long start = System.currentTimeMillis();
		
		//每次都是末尾添加数据
		for(int i=0;i<NUM;i++){
			arrayList.add("hello java ---> " + i);
		}

		long end = System.currentTimeMillis();
		System.out.println("array_tail_add 耗时:"+(end - start));
	}

	
	static class MyObject{
		private int id;
		private String name;
		public MyObject(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
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
			MyObject other = (MyObject) obj;
			if (id != other.id)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "MyObject [id=" + id + ", name=" + name + "]";
		}
	}
}
