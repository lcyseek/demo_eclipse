package com.seek.sync;

public class WorkThread extends Thread {

	public Object lock;

	public WorkThread(Object lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {

		if (this.lock == null)
			return;

		synchronized (lock) {
			System.out.println("WorkThread获取到了锁");
			try {
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName() + " wait for notification");

				lock.wait();
				//wait之后还是要重新抢Lock

				// 等待五秒
				//lock.wait(3000);

				System.out.println(Thread.currentThread().getName() + " wake up");

				for (int i = 0; i < 3; i++) {
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getName() + " doing " + i);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " finished");
		}
	}

}
