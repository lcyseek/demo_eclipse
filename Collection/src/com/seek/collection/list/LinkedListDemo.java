package com.seek.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * LinkedList:
 * 机制:底层的数据结构是基于双向循环链表的，且头结点中不存放数据
 * 线程:不是线程安全
 * 优缺点:查询慢，增删快|有大量的增加删除操作并且没有很多的随机访问元素的操作，应该首先LinkedList
 * 允许null,存放顺序和添加顺序是一致的。可添加重复元素
 * 
 * 新增加方法(相比于Collection):	按照数组index访问 添加
 * 			get(int index)
 * 			add(int index, E element)
 * 			remove(index)
 * 
 * 总结 ArrayList LinkedList的区别:
 * 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
 * 2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
 * 3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
 * 		这一点要看实际情况的。若只对单条数据插入或删除，ArrayList的速度反而优于LinkedList。
 * 		但若是批量随机的插入删除数据，LinkedList的速度大大优于ArrayList. 因为ArrayList每插入一条数据，要移动插入点及之后的所有数据
 *
 */

public class LinkedListDemo {

	public static void main(String[] args) {
		LinkedList<String> linkedList = new LinkedList<>();
		
		linkedList.add(null);
		linkedList.add("hello");
		linkedList.add("java");
		linkedList.add("Android");
		linkedList.add(1,"Tomcat");
		linkedList.addFirst("First");
		linkedList.addLast("last");
		showData(linkedList);
		showDataIterator(linkedList);

	
		ArrayList<String> list = new ArrayList<>();
		list.add("list-1");
		list.add("list-2");
		
		linkedList.addAll(2, list);
		showData(linkedList);
		
		System.out.println(linkedList.remove());
		System.out.println(linkedList.remove());
		
		showData(linkedList);
		
		System.out.println(linkedList.remove(1));
		System.out.println(linkedList.remove("Android"));
		System.out.println(linkedList.removeLast());
		
		showData(linkedList);
	}

	private static void showDataIterator(LinkedList<String> linkedList) {
		System.out.print("\nLinkedList 元素 : ");
		Iterator<String> iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.print(string+" ");
		}
		System.out.println("\n");
	}

	private static void showData(LinkedList<String> linkedList) {

		System.out.print("\nLinkedList 元素 : ");
		for (int i = 0; i < linkedList.size(); i++) {
			System.out.print(linkedList.get(i)+" ");
		}
		System.out.println("\n");
	}

}
