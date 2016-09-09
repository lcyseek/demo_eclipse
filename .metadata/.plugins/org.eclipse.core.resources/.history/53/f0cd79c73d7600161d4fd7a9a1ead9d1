package com.seek.sync;

/**
 * 
 * 当线程处于阻塞synchronized抢锁时，是不能被interrupt的。但是标记为可以被设置
 *
 */
public class Test4 {

	private static Object lock = new Object();
	
	public static void main(String[] args) {

		AThread aThread = new AThread();
		aThread.start();
		
		try {
			Thread.sleep(1000);
			BThread bThread = new BThread();
			bThread.start();
			
			Thread.sleep(1000);
			System.out.println("interrupt BThread");
			bThread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class AThread extends Thread{
		@Override
		public void run() {
			synchronized (lock) {
				try {
					System.out.println("AThread start");
					Thread.sleep(10000);
					System.out.println("AThread end");

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class BThread extends Thread{
		@Override
		public void run() {
			System.out.println("BThread locking.....");
			synchronized (lock) {
				System.out.println("BThread locked");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("BThread end");
		}
	}
}

