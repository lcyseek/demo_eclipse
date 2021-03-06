package com.seek.collection.queue;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
		
		//System.out.println("-----队列空 take 阻塞!-----");
//		queue.take();
		
		for(int i=0;i<10;i++){
			queue.put(String.valueOf(i));
		}
		
		traverse(queue);
		
		System.out.println("-----队列满 put 阻塞!-----");
		queue.put("11");
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
