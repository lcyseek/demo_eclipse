package com.seek.interrupt;

public class JoinThread extends Thread{
	@Override
	public void run() {
		
		try {
			
			//开启子线程，并且等待他结束
			Thread t = new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						Thread.sleep(90000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			t.setDaemon(true);
			t.start();
			System.out.println("start join.");
			t.join();
			//t.join(3000);
			System.out.println("end join.");
			
		} catch (Exception e) {
			e.printStackTrace();
			//因为抛出InterruptedException异常后，中断标示位会自动清除!!!! 所以此处检查标志位是false
			System.out.println("isInterrupted:"+isInterrupted());
		}
	}
}
