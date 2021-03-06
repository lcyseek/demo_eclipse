package com.seek.collection.queue;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		//此类的构造方法接受一个可选的公平 参数。当设置为 true 时，在多个线程的争用下，这些锁倾向于将访问权授予等待时间最长的线程。否则此锁将无法保证任何特定访问顺序
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10,true);
		
		
	    //System.out.println("-----队列空 take 阻塞!-----");
	    //queue.take();//队列空，造成阻塞！
		
		for(int i=0;i<10;i++){
			queue.add(String.valueOf(i));
		}
		
		traverse(queue);
		
		//超过元素大小限制，添加失败
//		queue.add("11");
	
		//只有用put 和 take两个方法才会有可能阻塞！
		System.out.println("-----队列满 put 阻塞!-----");
		queue.put("12344");
	}
	
	private static void traverse(Queue<String> deque) {
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
