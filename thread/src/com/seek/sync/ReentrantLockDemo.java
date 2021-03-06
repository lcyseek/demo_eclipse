package com.seek.sync;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 目前在Java中存在两种锁机制：synchronized和Lock 
 * 
 * Lock 和 Synchronized 的区别 
 * 主要相同点：
 * 		Lock能完成synchronized所实现的所有功能
 * 
 * 不同点： 
 * 		ReentrantLock 拥有Synchronized相同的并发性和内存语义，此外还多了 锁投票，定时锁等候和中断锁等候
 * 		线程A和B都要获取对象O的锁定，假设A获取了对象O锁，B将等待A释放对O的锁定. 如果使用 synchronized,如果A不释放，B将一直等下去，不能被中断 如果 使用ReentrantLock，如果A不释放，可以使B在等待了足够长的时间以后，中断等待，而干别的事情
 * 	
 * 		lock 必须在 finally 块中释放。否则，如果受保护的代码将抛出异常，锁就有可能永远得不到释放！
 * 		而使用同步 synchronized 块时，JVM 将确保锁会获得自动释放
 * 
 * ReentrantLock获取锁定与三种方式 
 * 		a) lock(), 如果获取了锁立即返回，如果别的线程持有锁，当前线程则一直处于休眠状态，直到获取锁 
 * 		b) tryLock(), 如果获取了锁立即返回true，如果别的线程正持有锁，立即返回false; 
 * 		c) tryLock(long timeout,TimeUnitunit),如果获取了锁定立即返回true，如果别的线程正持有锁，会等待参数给定的时间，在等待的过程中，如果获取了锁定，就返回true，如果等待超时，返回false; 
 * 		d) lockInterruptibly:如果获取了锁定立即返回，如果没有获取锁定，当前线程处于休眠状态，直到获得锁定，或者当前线程被别的线程中断
 *
 *
 * 在资源竞争不是很激烈的情况下，Synchronized的性能要优于ReetrantLock，但是在资源竞争很激烈的情况下，
 * Synchronized的性能会下降几十倍，但是ReetrantLock的性能能维持常态；
 */

public class ReentrantLockDemo {

	//它允许您选择想要一个 公平（fair）锁，还是一个 不公平（unfair）锁。公平锁使线程按照请求锁的顺序依次获得锁；
	//而不公平锁则允许讨价还价，在这种情况下，线程有时可以比先请求锁的其他线程先得到锁。
	//公平保证了锁是非常健壮的锁，有很大的性能成本。默认是false
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void doWork() {
		
		lock.lock();
		System.out.println("doWork获取到了锁");
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}

		System.out.println("doWork 执行完毕");
	}

	public static void doLove(int time) {
		System.out.println("doLove开始获取锁");
		
		boolean b = false;
		try {
			if(time != 0){
				b = lock.tryLock(time, TimeUnit.SECONDS);
				if(!b){
					System.out.println("doLove 没有获取到锁,结束");
					return ;
				}
			}else{
				lock.lock();
				b = true;
			}
			
			System.out.println("doLove获取到了锁");
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(b == true)
				lock.unlock();
		}
		
		System.out.println("doLove 执行完毕");
	}

	public static void main(String[] args) throws InterruptedException {
//		test1();
//		test2();
		test3();
	}


	@SuppressWarnings("unused")
	private static void test1() throws InterruptedException {
		new Thread(new Runnable() {
			public void run() {
				doWork();
			}
		}).start();

		Thread.sleep(200);
		doLove(3);
	}
	
	private static void test2() throws InterruptedException {
		// TODO Auto-generated method stub
		new Thread(new Runnable() {
			public void run() {
				doWork();
			}
		}).start();

		Thread.sleep(200);
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				doLove(3);
			}
		});
		t.start();
		
		Thread.sleep(1000);
		System.out.println("能否打断获取锁的状态");
		t.interrupt();//lock.lock()不能被打断，lock.tryLock()可以被打断
	}


	private static void test3() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new MyRunnable("线程1"));
		Thread t2 = new Thread(new MyRunnable("线程2"));
		
		t1.start();
		
		//保证t1先运行
		Thread.sleep(200);
		t2.start();
		
		Thread.sleep(2000);
		t2.interrupt();
		
		/**
		 * 结果分析:
		 * a--> lock.lock()  t2的lock.lock不会优先考虑t2.interrupt()。但是抢到了锁之后，因为设置了 interrupt的标志位，所以会抛出中断异常
		 * b--> lock.lockInterruptibly() t2的lockInterruptibly优先考虑响应中断,所以回退出抢锁
		 * c--> lock.tryLock(6, TimeUnit.SECONDS) 也会响应中断
		 */
	}

	static class MyRunnable implements Runnable {

		private String name;

		public MyRunnable(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			try {
				System.out.println(name + " 开始抢锁");
				
//				lock.lock();
				lock.lockInterruptibly();
				
//				if(!lock.tryLock()){
//					System.out.println(name+" 没有获取到锁");
//					return;
//				}
				
//				if(!lock.tryLock(6, TimeUnit.SECONDS)){
//					System.out.println(name+" 没有获取到锁");
//					return;
//				}

				System.out.println(name + " 抢到了锁");
				Thread.sleep(8000);
				lock.unlock();
				System.out.println(name + " 结束");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
