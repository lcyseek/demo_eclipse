package com.seek.collection.queue;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2.intValue() - o1.intValue();
				
//				if(o2.intValue() > o1.intValue())
//					return 1;
//				else if(o2.intValue() < o1.intValue())
//					return -1;
//				else
//					return 0;
			}
		};
		
		//如果不提供Comparator的话，优先队列中元素默认按自然顺序排列，也就是数字默认是小的在队列头，字符串则按字典序排列。
		//无边界！
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(10, comparator);
		
		priorityQueue.offer(2);
		priorityQueue.offer(7);
		priorityQueue.offer(1);
		priorityQueue.offer(9);
		priorityQueue.offer(6);
		priorityQueue.offer(3);
		priorityQueue.offer(5);
		priorityQueue.offer(4);
		priorityQueue.offer(0);
		priorityQueue.offer(8);

		priorityQueue.offer(100);
		priorityQueue.offer(100);

		traverse1(priorityQueue);
 
	}
	
	//迭代器遍历并没有按照优先级。
	@SuppressWarnings("unused")
	private static void traverse(PriorityQueue<Integer> deque) {
		Iterator<Integer> iterator = deque.iterator();

		String s = "[";
		while (iterator.hasNext()) {
			String string = iterator.next()+"";
			if (iterator.hasNext())
				s += string + ",";
			else
				s += string;
		}
		s += "]";
		System.out.println(s);
	}
	
	private static void traverse1(PriorityQueue<Integer> deque) {

		String s = "[";
		Integer ret;
		while( (ret = deque.poll()) != null){
			s += ret.intValue()+" ";
		}
		s += "]";
		System.out.println(s);
	}

}
