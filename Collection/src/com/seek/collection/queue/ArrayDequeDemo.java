package com.seek.collection.queue;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * 
 * 1.ArrayDeque, （数组双端队列） 
 * 2.PriorityQueue, （优先级队列） 
 * 3.ConcurrentLinkedQueue, （基于链表的并发队列） 
 * 4.DelayQueue, （延期阻塞队列）（阻塞队列实现了BlockingQueue接口） 
 * 5.ArrayBlockingQueue, （基于数组的并发阻塞队列） 
 * 6.LinkedBlockingQueue, （基于链表的FIFO阻塞队列） 
 * 7.LinkedBlockingDeque, （基于链表的FIFO双端阻塞队列） 
 * 8.PriorityBlockingQueue, （带优先级的无界阻塞队列） 
 * 9.SynchronousQueue （并发同步阻塞队列） 
 *
 */

/*
 * 数组双端队列没有容量限制，使他们增长为必要支持使用
 * 不是线程安全的
 * null元素被禁止使用
 * 
 * */
public class ArrayDequeDemo {

	public static void main(String[] args) {
		
		//此构造函数用于创建一个空数组双端队列容纳16个元素的初始容量。
		ArrayDeque<String> deque = new ArrayDeque<>();
		
		//在deque队列的末尾添加元素
		deque.add("元素1");
		deque.add("元素2");
		deque.add("元素3");
		deque.add("元素4");
		
		deque.addFirst("元素0");
		deque.addLast("元素5");

		
		showData(deque);
		
		//检索并移除此deque队列表示的队列的头部
		System.out.println("poll:"+deque.poll());
		System.out.println("poll:"+deque.poll());
		System.out.println("peek:"+deque.peek());//返回队列头部的元素 不会移除
		
		showData(deque);

	}

	private static void showData(ArrayDeque<String> deque) {
		Iterator<String> iterator = deque.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.println(string);
		}
	}

}
