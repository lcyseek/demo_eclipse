package com.seek.sync;

public class NotifyThread extends Thread {

	public Object lock;
	private int millis;

	public NotifyThread(Object lock, int millis) {
		super();
		this.lock = lock;
		this.millis = millis;
	}

	@Override
	public void run() {

		try {
			
			synchronized (lock) {
				System.out.println("NotifyThread获取到了锁");
				Thread.sleep(millis);

				System.out.println("开始唤醒");
				lock.notifyAll();
				System.out.println("已经发送唤醒,本进程睡眠一段时间,这期间还是保持Lock");

				Thread.sleep(millis);
				System.out.println("睡眠结束");
				// 因为要睡眠一段时间，所以即使发送了notifyAll，另外的线程也不能获取到lock的锁，不会立刻执行。只有当这释放了锁，其他线程才能执行
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
