package com.seek.collection.queue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		
		Comparator<Integer> comparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2.intValue() - o1.intValue();
			}
		};
		
		PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(10, comparator);
		
//		System.out.println("--队列空 阻塞了-");
//		queue.take();
		
		
		queue.put(2);
		queue.put(7);
		queue.put(1);
		queue.put(9);
		queue.put(6);
		queue.put(3);
		queue.put(5);
		queue.put(4);
		queue.put(0);
		queue.put(8);

		queue.put(100);
		queue.put(100);
		
		System.out.println("--无界队列添加元素不会阻塞--");
		traverse1(queue);
	}
	
	private static void traverse1(PriorityBlockingQueue<Integer> deque) throws InterruptedException {

		Integer ret;
		//当队列为空时，阻塞消费
		while( (ret = deque.take()) != null){
			System.out.println(ret.intValue() +" ");
		}
	}

}
