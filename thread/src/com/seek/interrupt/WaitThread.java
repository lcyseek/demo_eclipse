package com.seek.interrupt;

public class WaitThread implements Runnable{
	
	public static Object lock = new Object();
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (lock) {
			try {
				System.out.println("WaitThread start wait.");
				lock.wait();
				System.out.println("WaitThread end wait.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
