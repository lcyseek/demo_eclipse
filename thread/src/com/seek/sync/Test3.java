package com.seek.sync;

public class Test3 {

	private static Object lock = new Object();
	
	public static void main(String[] args) throws InterruptedException {

		MyThread thread = new MyThread();
		thread.start();
		
		Thread.sleep(3000);
		synchronized (lock) {
			lock.notify();
		}
		
	}
	
	static class MyThread extends Thread{
		int x = 11;
		@Override
		public void run() {
			synchronized (lock) {
				
				try {
					if(x < 100){
						System.out.println("x<100 wait");
						lock.wait();
						System.out.println("after wait.");
					}else{
						System.out.println("else.......");
					}
				} catch (Exception e) {
				}

			}
		}
	}

}
