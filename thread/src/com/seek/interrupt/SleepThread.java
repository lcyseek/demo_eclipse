package com.seek.interrupt;

public class SleepThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			while (true) {
				System.out.println("I am running");

				// 休眠一断时间,中断时会抛出InterruptedException 调用sleep的时候锁并没有被释放。
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			//如果触发中断异常了，中断状态被清除，所以下面检测isInterrupted() 一直为false
			//因为抛出InterruptedException异常后，中断标示位会自动清除.Thread.currentThread().interrupt();这句可以来设置中断状态。	
			//Thread.currentThread().interrupt();
			System.out.println("isInterrupted:"+isInterrupted());//false
			e.printStackTrace();
		}
	}

}
