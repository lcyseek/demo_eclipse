package com.seek.interrupt;

import java.io.IOException;

/**
 * 
 * 3.interrupted()测试当前线程是否已经中断。线程的中断状态 由该方法清除.换句话说，如果连续两次调用该方法，则第二次调用将返回 false
 * 
 * 因为抛出InterruptedException异常后，中断标示位会自动清除
 *
 * Thread.interrupt()方法 
 * 会使正在运行的线程中断状态(interrupted status) 被置位。
 * 被阻塞的线程抛出一个中断信号，这样线程就得以退出阻塞的状态。 （没有占用CPU运行的线程是不可能给自己的中断状态置位的。这就会产生一个InterruptedException异常）
 * 		如果线程被Object.wait, Thread.join和Thread.sleep三种方法之一阻塞，那么，它将接收到一个中断异常（InterruptedException），从而提早地终结被阻塞状态。
 * 		抛出InterruptedException异常后，中断标示位会自动清除
 * 
 * interrupt方法并不是强制终止线程，它只能设置线程的interrupted状态
 * 
 */
public class Test {

	public static void main(String[] args) throws InterruptedException, IOException {
//		test1();
//		test2();
		test3();
	}
	
	
	private static void test3() throws InterruptedException {
		Thread3 thread3 = new Thread3();
		thread3.start();
		
		Thread.sleep(2000);
		thread3.interrupt();
	}


	@SuppressWarnings("unused")
	private static void test2() throws InterruptedException {
		// 分线程如果处于Object.wait,Thread.join和Thread.sleep三种方法之一阻塞的情况下，
		// 我们调用interrupt()方法的话，就会抛出中断异常（InterruptedException），从而提早地终结被阻塞状态。

//		Thread thread = new SleepThread();
//		 Thread thread = new Thread(new WaitThread());
//		 Thread thread = new SocketThread();
		 Thread thread = new JoinThread();

		thread.start();
		Thread.sleep(4000);
		System.out.println("发送 Interrupted 信号!");
		thread.interrupt();

		// SocketThread.socket.close();
		// 假如是Socket阻塞和I/O阻塞的话又是什么情况呢？可以看到不起作用.要打断他需要使用socket.close();		
	}


	@SuppressWarnings("unused")
	private static void test1() throws InterruptedException {
		 Thread thread = new NormalThread();
		 thread.start();
		
		Thread.sleep(2000);
		 thread.interrupt();
		// 分析:运行这个程序，我们发现调用interrupt()后，NormalThread 里面确实可以检测到isInterrupted()为。
		// 但是也仅仅是检测到而已。如果不自己退出，还是会一直运行
	}


	public static class Thread3 extends Thread {
		boolean isStop = false;

		@Override
		public void run() {
			System.out.println("start thread");

			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();

					// 因为抛出异常后中断标示会被清除 所以下面会是false
					System.out.println(Thread.currentThread().isInterrupted());
					// TODO Auto-generated catch block

					// 中不中断由自己决定，如果需要真真中断线程，则需要重新设置中断位，如果
					// 不需要，则不用调用，那么会进入下一次的while循环！记住一定要用这句，否则isInterrupted()为false
					Thread.currentThread().interrupt();
				}
			}

			System.out.println("end thread");
		}
	}
}
