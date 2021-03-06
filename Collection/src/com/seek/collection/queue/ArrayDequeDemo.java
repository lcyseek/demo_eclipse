package com.seek.collection.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * 
 * 1.ArrayDeque, （数组双端队列） 2.PriorityQueue, （优先级队列） 3.ConcurrentLinkedQueue,
 * （基于链表的并发队列） 4.DelayQueue, （延期阻塞队列）（阻塞队列实现了BlockingQueue接口）
 * 5.ArrayBlockingQueue, （基于数组的并发阻塞队列） 6.LinkedBlockingQueue, （基于链表的FIFO阻塞队列）
 * 7.LinkedBlockingDeque, （基于链表的FIFO双端阻塞队列） 8.PriorityBlockingQueue,
 * （带优先级的无界阻塞队列） 9.SynchronousQueue （并发同步阻塞队列）
 *
 */

/*
 * 数组双端队列没有容量限制，使他们增长为必要支持使用 不是线程安全的 null元素被禁止使用
 * 
 */
public class ArrayDequeDemo {

	public static void main(String[] args) {

		// 此构造函数用于创建一个空数组双端队列容纳16个元素的初始容量。
		ArrayDeque<String> deque = new ArrayDeque<>();
		for (int i = 0; i < 16; i++) {
			// 每次都在队尾
			deque.offer(String.valueOf(i));
		}
		traverse(deque);

//		deque.clear();
//		String head = deque.remove();
		String head = deque.poll();
		System.out.println("head element = " + head);
		traverse(deque);
		
//		deque.clear();
//		String item = deque.element();
		String item = deque.peek();
		System.out.println("element = "+ item);
		traverse(deque);
		
//		deque.getFirst();
//		deque.getLast();
//		deque.removeFirst();
//		deque.removeLast();
		
	}

	private static void traverse(Deque<String> deque) {
		Iterator<String> iterator = deque.iterator();

		String s = "[";
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			if (iterator.hasNext())
				s += string + ",";
			else
				s += string;
		}
		s += "]";
		System.out.println(s);
	}

}
