package com.seek.collection.queue;


/**
 * 
 * Queue{
 * 
 * 		boolean add(E e); 	增加一个元索 如果队列已满，则抛出一个IIIegaISlabEepeplian异常
 * 		boolean offer(E e);	添加一个元素并返回true       	如果队列已满，则返回false
 * 
 * 		E remove();			移除并返回队列头部的元素    	如果队列为空，则抛出一个NoSuchElementException异常
 * 		E poll();	  		移除并返回队列头部的元素		如果此deque队列为空，则返回null
 * 。
 * 		E element();		返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
 * 		E peek();			返回队列头部的元素             如果队列为空，则返回null
 * 
 * }
 * 
 * Queue
 * 		1.PriorityQueue 
 * 				PriorityQueue优先级队列是不同于先进先出队列的另一种队列。每次从队列中取出的是具有最高优先权的元素。PriorityQueue保存队列元素的顺序并不是按照加入队列的顺序，而是按照队列元素的大小进行重新排序，这点从它的类名也可以看出来
 * 				他是具体的类，没有子类
 * 		2.Deque
 *				Deque接口代表一个"双端队列"，双端队列可以同时从两端来添加、删除元素，因此Deque的实现类既可以当成队列使用、也可以当成栈使用
 * 				子类：
 * 					ArrayDeque
 * 					LinkedList
 * 		3.BlockingQueue
 * 				新增加put take 阻塞的方法。poll(timeout, unit)超时方法
 * 				可以是限定容量的。它在任意给定时间都可以有一个remainingCapacity，超出此容量，便无法无阻塞地put 附加元素。没有任何内部容量约束的BlockingQueue 总是报告Integer.MAX_VALUE 的剩余容量
 * 				子类:
 * 					ArrayBlockingQueue	基于数组的阻塞队列实现
 * 					LinkedBlockingQueue	基于链表的阻塞队列
 * 					DelayQueue	是一个无界的BlockingQueue，往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞。用于放置实现了Delayed接口的对象，其中的对象只能在其到期时才能从队列中取走。这种队列是有序的，即队头对象的延迟到期时间最长.
 * 					PriorityBlockingQueue 这个并不是PriorityQueue的子类，它自己内部也实现了比较排序，所以不需要继承PriorityQueue.基于优先级的阻塞队列（优先级的判断通过构造函数传入的Compator对象来决定），但需要注意的是PriorityBlockingQueue并不会阻塞数据生产者，而只会在没有可消费的数据时，阻塞数据的消费者。因此使用的时候要特别注意，生产者生产数据的速度绝对不能快于消费者消费数据的速度，否则时间一长，会最终耗尽所有的可用堆内存空间
 * 					SynchronousQueue
 * 
 * 
 */

public class Entry {
}
