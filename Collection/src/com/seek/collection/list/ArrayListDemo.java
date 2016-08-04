package com.seek.collection.list;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * ArrayList:
 * 机制:基于数组实现的,是一个动态数组，其容量(默认10,如果不够了，每次增加1.5倍左右原有容量)能自动增长.
 * 线程:不是线程安全
 * 优缺点:查询快，增删慢
 * 允许null,存放顺序和添加顺序是一致的。可添加重复元素 有序
 * 
 * 新增加方法(相比于Collection):	按照数组index访问 添加
 * 			get(int index)
 * 			add(int index, E element)
 * 			remove(index)
 */

public class ArrayListDemo {

	public static void main(String[] args) {
		//默认容量10
		ArrayList<String> arrayList = new ArrayList<>();
		
		addData(arrayList);
		
		/*Collection方法的实现*/
		System.out.println("ArrayList size:"+arrayList.size());//0 size是实际元素的数量而不是数组的容量
		System.out.println("ArrayList is empty:"+arrayList.isEmpty());//容器是否为空
		System.out.println("ArrayList是否包含shanghai:"+arrayList.contains("shanghai"));
		System.out.println("ArrayList移除元素shanghai:"+arrayList.remove("shanghai"));
		
		showDataByIterator(arrayList);
		showData(arrayList);
	
		//生成一个子集合
		ArrayList<String> subList = new ArrayList<>();
		subList.add("sublist--1");
		subList.add("sublist--2");
		
		//添加子集合
//		arrayList.addAll(subList);
		arrayList.addAll(1, subList);
		showData(arrayList);

		//删除包含子集合里的所有元素
		subList.add("Andorid");
		arrayList.removeAll(subList);
		showData(arrayList);
		
		//判断是否完全包含子集合
		subList.clear();
		subList.add("beijing");
		subList.add("tianjin");
		subList.add("tianjin");
		System.out.println("ArrayList containsAll subList:"+arrayList.containsAll(subList));

		//两个集合求交集，只保留交集数据,换句话说，移除此collection中未包含在指定collection中的所有元素
		//如果List集合对象由于调用retainAll方法而发生更改，则返回 true
		boolean bool = arrayList.retainAll(subList);
		System.out.println("ArrayList retainAll:"+bool);
		showData(arrayList);
	}
	

	private static void showData(ArrayList<String> arrayList) {
		/*方法1*/
		System.out.print("ArrayList 元素:");
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.print(arrayList.get(i)+" ");
		}
		System.out.println("\n");
	}

	private static void showDataByIterator(ArrayList<String> arrayList) {
		/*方法2*/
		System.out.print("ArrayList 元素:");
		Iterator<String> iterator = arrayList.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.print(string+" ");
			
			if(string.equals("shenzhen"))
				iterator.remove();//唯一一个可以在遍历时删除元素的
			
			//arrayList.add("新元素");//不能在迭代器迭代时添加元素.
		}
		System.out.println("\n");
	}


	private static void addData(ArrayList<String> arrayList) {
		arrayList.add("beijing");
		arrayList.add("nanjing");
		arrayList.add("shanghai");
		arrayList.add("guangzhou");
		arrayList.add("tianjin");
		arrayList.add("shenzhen");
		arrayList.add(1, "wuhan");
	}

}
