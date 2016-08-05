package com.seek.interrupt;

public class NormalThread extends Thread{
	private double d = 0.0;

	@Override
	public void run() {
		// 死循环执行打印"I am running!" 和做消耗时间的浮点计算
		while (true) {
			for (int i = 0; i < 900000; i++) {
				d = d + (Math.PI + Math.E) / d;
			}
			// 给线程调度器可以切换到其它进程的信号
			//yield并不意味着退出和暂停，只是，告诉线程调度如果有人需要，可以先拿去，我过会再执行，没人需要，我继续执行）.调用yield的时候锁并没有被释放。
			Thread.yield();
			System.out.println("isInterrupted="+isInterrupted());
		}
	}
}
