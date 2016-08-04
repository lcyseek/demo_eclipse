package com.seek.collection.queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 是一个由数组支持的有界阻塞队列
 * 试图向已满队列中放入元素会导致报错；试图从空队列中提取元素将导致类似阻塞
 *
 */
public class ArrayBlockingQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		
		//初始化容量
		ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(7);
		
		String s0 = queue.poll();//移除并返回队列头部的元素    如果队列为空，则返回null
		System.out.println("s0="+s0);
		
//		queue.poll(3, TimeUnit.SECONDS);//如果队列里没有元素，则等待时间

		
//		String s1 = queue.take();//移除并返回队列头部的元素     如果队列为空，则阻塞
//		System.out.println("s1="+s1);
		
		initData(queue);

		System.out.println("队列里还可以添加多少个元素:"+queue.remainingCapacity());

//		queue.add("h");//这句话会报错。队列满
		

		
	}

	private static void initData(ArrayBlockingQueue<String> queue) {
		queue.add("a");
		queue.add("b");
		queue.add("c");
		queue.add("d");
		queue.add("e");
		queue.add("f");
		queue.add("g");
	}

}
