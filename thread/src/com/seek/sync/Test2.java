package com.seek.sync;
import java.io.IOException;


/**
 * 
 * wait,notify和notifyAll都只能在一个锁对象上调用,否则会发生如下异常:
 * java.lang.IllegalMonitorStateException: current thread not owner
 * 
 * wait会释放占有的锁,notify和notifyAll不会释放占用的锁.
 * 
 * 
 * 如果一个线程处于了阻塞状态（如线程调用了thread.sleep、thread.join、thread.wait、
 * 1.5中的condition.await、以及可中断的通道上的 I/O 操作方法后可进入阻塞状态），
 * 则在线程在检查中断标示时如果发现中断标示为true，则会在这些阻塞方法（sleep、join、wait、1.5中的condition.
 * await及可中断的通道上的 I/O 操作方法）
 * 调用处抛出InterruptedException异常，并且在抛出异常后立即将线程的中断标示位清除，即重新设置为false。
 * 抛出异常是为了线程从阻塞状态醒过来，并在结束线程前让程序员有足够的时间来处理中断请求。
 * 
 */

public class Test2 {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		Object lock = new Object();
		
		Thread thread = new WorkThread(lock);
		thread.start();
		
		NotifyThread notifyThread = new NotifyThread(lock, 8000);
		notifyThread.start();
	}

}

class WorkThread extends Thread {

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
				System.out.println("WorkThread" + " wait for notification");

				lock.wait();
				//wait之后还是要重新抢Lock

				// 等待五秒
				//lock.wait(3000);

				System.out.println("WorkThread" + " wake up");

				for (int i = 0; i < 3; i++) {
					Thread.sleep(1000);
					System.out.println("WorkThread" + " doing " + i);
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println(Thread.currentThread().getName() + " finished");
		}
	}
}

class NotifyThread extends Thread {

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

