package com.seek.sync;

/**
 * 目前在Java中存在两种锁机制：synchronized和Lock
 *
 */

public class Test {

	
	public static void main(String[] args) throws InterruptedException {

//		test_1();
//		test_2();
		test_3();
	}

	private static void test_3() throws InterruptedException {
		Object lock = new Object();
		new SyncThread(lock, "线程1").start();
		
		//这个线程会阻塞抢锁，直到上面线程释放锁，这个线程才会得到执行。interrupt不会妨碍获得锁的。仅仅会设置中断状态！下面的线程获得锁后，会检测到interrupt中断，继而抛出异常
		SyncThread thread = new SyncThread(lock, "线程2");
		thread.start();
		Thread.sleep(1000);
		thread.interrupt();

	}

	@SuppressWarnings("unused")
	private static void test_1() {
		// TODO Auto-generated method stub
		//这种方法可能会造成同一时间卖出相同的票
		Tick tick = new Tick();
		new Thread(tick).start();
		new Thread(tick).start();
		new Thread(tick).start();
	}
	
	@SuppressWarnings("unused")
	private static void test_2() {
		TickWindow tick = new TickWindow();
		new Thread(tick).start();
		new Thread(tick).start();
		new Thread(tick).start();
	}
}



class Tick implements Runnable{

	private int tick = 50;
	
	@Override
	public void run() {
		while (tick != 0) {
			System.out.println(Thread.currentThread().getName() + " sail -- " + tick--);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


/**
 * 
 *	synchronized(对象)//这个对象可以为任意对象 
 *	{ 
 *		需要被同步的代码 
 *	} 
 *
 */

class TickWindow implements Runnable{

	private int tick = 50;

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				if(tick > 0){
					System.out.println(Thread.currentThread().getName() + " sail -- " + tick--);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					break;
				}
				
			}
		}
	}
}

class SyncThread extends Thread{
	private Object lock;
	private String name;
	
	public SyncThread(Object lock,String name) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
		this.name = name;
	}
	@Override
	public void run() {
		synchronized (lock) {
			System.out.println(name+" 获取到了锁");
			try {
				Thread.sleep(10000);//sleep不会释放锁
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name+"结束");
		}
	}
}
