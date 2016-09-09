package com.seek.sync;
import java.io.IOException;


/**
 * 
 * wait,notify和notifyAll都只能在一个锁对象上调用,否则会发生如下异常:
 * java.lang.IllegalMonitorStateException: current thread not owner
 * 
 * 导致当前的线程等待,直到其他线程调用此对象的 notify()方法或 notifyAll()方法.
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
