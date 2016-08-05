package com.seek.pool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

//使用线程工厂就无需再手工编写对 new Thread 的调用了
public class ThreadFactoryDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newFixedThreadPool(5,new SimpleThreadFactory());
		for(int i=0;i<5;i++){
			pool.execute(new Task(i));
		}
		
		System.out.println("main end");
	}

	
	static class Task implements Runnable{
		int taskID;
		public Task(int taskID) {
			this.taskID = taskID;
		}
		
		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName()+"--taskId: "+taskID );  	
		}
		
	}
}

class SimpleThreadFactory implements ThreadFactory{

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(r);
		thread.setName("自定义的线程工厂");
//		thread.setDaemon(true);
		return thread;
	}
	
}
