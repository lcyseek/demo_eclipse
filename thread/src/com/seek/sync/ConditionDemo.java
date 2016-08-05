package com.seek.sync;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 
 * Condition是在java 1.5中才出现的，它用来替代传统的Object的wait()、notify()实现线程间的协作，
 * 
 * Lock对应Synchronized，使用之前都要先获取锁 
 * 				Object      Condition 
 * 休眠           wait         await 
 * 唤醒个线程      notify       signal 
 * 唤醒所有线程    notifyAll    signalAll 
 * 
 * Condition 的方法与 wait 、 notify 和 notifyAll 方法类似，分别命名为 await 、 signal 和 signalAll ，因为它们不能覆盖 Object 上的对应方法。
 * Condition的await()和signal()方法，都必须在lock保护之内，就是说必须在lock.lock()和lock.unlock之间才可以使用
 * Condition它更强大的地方在于：能够更加精细的控制多线程的休眠与唤醒。
 * 
 * 例如，假如多线程读/写同一个缓冲区：当向缓冲区中写入数据之后，唤醒"读线程"；当从缓冲区读出数据之后，唤醒"写线程"；
 * 如果采用Object类中的wait(), notify(), notifyAll()实现该缓冲区，当向缓冲区写入数据之后需要唤醒"读线程"时，
 * 不可能通过notify()或notifyAll()明确的指定唤醒"读线程"，而只能通过notifyAll唤醒所有线程(
 * 但是notifyAll无法区分唤醒的线程是读线程，还是写线程)。
 *
 */

public class ConditionDemo {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Basket basket = new Basket();
		
		Customer c1 = new Customer(basket, "c1");

		Producer p1 = new Producer(basket,"p1");
		Producer p2 = new Producer(basket,"p2");
		
		
		ExecutorService service = Executors. newCachedThreadPool();
        service.execute( c1);
        service.execute( p1);
        //service.execute( p2);
	}

}



// 我们假设有一个篮子，最多可以放10个苹果，有多个人可以放苹果，也有多个人可以拿走苹果。
class Apple {
	private String appName;

	public Apple(String appName) {
		super();
		this.appName = appName;
	}

	@Override
	public String toString() {
		return "Apple [appName=" + appName + "]";
	}
}

class Basket {
	ArrayList<Apple> list = new ArrayList<>();
	Lock lock = new ReentrantLock();
	Condition noEmpty = lock.newCondition();
	Condition noFull = lock.newCondition();

	public void put(Apple apple) {
		// 获取锁
		lock.lock();
		System.out.println(apple + " 获取了锁");
		try {
			if (list.size() >= 10) {
				noFull.await();
			}
			System.out.println("目前总共有资源 : " + list.size()+"个" + " 准备放入一个\n");
			list.add(apple);
			noEmpty.signalAll();

		} catch (Exception e) {
		} finally {
			lock.unlock();
		}
	}
	
	
	public Apple take(){
		lock.lock();
		Apple apple = null;
		try {
			if(list.size()<=0){
				noEmpty.await();
			}
			
			System.out.println("目前总共有资源 : " + list.size()+"个" + " 准备取走一个\n");
			apple = list.remove(0);
			
			noFull.signalAll();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			lock.unlock();
		}
		
		return apple;
	}
}
	
class Producer implements Runnable{
		private Basket basket;
		private String name;
		

		public Producer(Basket basket, String name) {
			super();
			this.basket = basket;
			this.name = name;
		}


		@Override
		public void run() {
			while(true){
				try {
					basket.put(new Apple(name + (int)(Math.random()*1000)));
//					System.out.println(name + " 生产");
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
		}
}
	
	
class Customer implements Runnable{
		private Basket basket;
		@SuppressWarnings("unused")
		private String name;
		

		public Customer(Basket basket, String name) {
			super();
			this.basket = basket;
			this.name = name;
		}


		@SuppressWarnings("unused")
		@Override
		public void run() {
			while(true){
				try {
					Apple apple = basket.take();
//					System.out.println(name +  "消费 : " +apple);
					Thread.sleep(5000);
				} catch (Exception e) {
				}
			}
		}
}
