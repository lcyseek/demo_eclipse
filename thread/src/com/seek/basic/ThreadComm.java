package com.seek.basic;


public class ThreadComm {
	
	public static void main(String[] args) {
		System.out.println("main--->start");
		
		/**
		 * Thread start()和run()方法的区别
		 * 1.start()它的作用是启动一个新线程，新线程会执行相应的run()方法
		 * 2.run()就和普通的成员方法一样，可以被重复调用。单独调用run()的话，会在当前线程中执行run()，而并不会启动新线程！
		 */
		
//		MyThread myThread = new MyThread(runnable);
//		myThread.start();
		
//		test_1();
		test_2();
		
		System.out.println("main--->end");
	}
	
	
	private static RunnableOne runnable = new RunnableOne();
	
	@SuppressWarnings("unused")
	private static void test_2() {
		Thread thread = new Thread(runnable);
//		thread.setDaemon(true);
		thread.start();
		
		Thread thread2 = new Thread(runnable);
		thread2.start();
		
//		public static final int MAX_PRIORITY 10 
//		public static final int MIN_PRIORITY 1 
//		public static final int NORM_PRIORITY 5 
		System.out.println("priority="+thread.getPriority());//5
	}


	public static void test_1(){
		
		//#1
		new Thread(new Runnable() {
			public void run() {
				//thread name Thread-0
				System.out.println("thread name " + Thread.currentThread().getName());
			}
		}).start();
		
		
		//#2
		new Thread(){
			public void run() {
				System.out.println("thread name " + Thread.currentThread().getName());
			};
		}.start();
	}
}

/**
 * 
 * 这个类是为了测试线程的start和run方法的区别
 */
class MyThread extends Thread{
	
	public MyThread(Runnable runnable) {
		super(runnable);
	}
	
	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		System.out.println("MyThread--->start");
		
		//父类的start()--->start0()--->run()
		super.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("MyThread--->run ");
		
		//这句话，会调用父类的run方法，run方法会调用传入父类的Runnable对象里的run方法。
		//如果没有这个方法则不会调用父类的run。
		super.run();
	}
}


class RunnableOne implements Runnable {
	
	private int times = 0;
	
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Runnable : thread name " + Thread.currentThread().getName());
		System.out.println("times : " + (++times));
	}
}
