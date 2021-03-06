package com.seek.pool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Callable和Future，通过它们可以在任务执行完毕之后得到任务执行结果。
 *
 * Callable接口类似于Runnable,Callable功能更强大一些，被线程执行后，可以返回值，这个返回值可以被Future拿到
 * 1.Callable 使用 call（） 方法， Runnable 使用 run() 方法 
 * 2.call() 可以返回值， 而 run()方法不能返回。 
 * 3.call() 可以抛出受检查的异常，而run()不能抛出受检查的异常
 * 
 * public interface Callable<V> { 
 * 		V call() throws Exception; 
 * }
 * 
 * Future
 * Future就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果
 * FutureTask FutureTask是Future接口的一个唯一实现类.它继成了Runnable和Future接口,
 * 所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值
 * 
 * 那么怎么使用Callable呢？
 * Callable 两种使用方式:
 * 		1)Callable交给线程池运行，会返回一个Future对象. 
 * 		2)用Futrue包装一下，然后把Future交给Thread运行
 * 
 * 
 */
public class CallableAndFutureDemo {

	public static void main(String[] args) throws Exception {
//		test1();
//		test2();
		test3();
//		useCallableOne();
//		useCallableTwo();
		
//		futureMethod();
//		futureMethod1();
	}


	private static void test1() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		
		//一秒后执行
		Future future = pool.submit(new Runnable() {
			public void run() {
				System.out.println("start");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("end.");
			}
		});
		//如果是Runnable对象，那么拿不到结果！
		System.out.println("runnable is done:"+future.isDone());
		Thread.sleep(500);
		System.out.println("runnable is done:"+future.isDone());
		Thread.sleep(500);
		System.out.println("runnable is done:"+future.isDone());
		Thread.sleep(500);
		System.out.println("runnable is done:"+future.isDone());
		Thread.sleep(500);
		System.out.println("runnable is done:"+future.isDone());
		Thread.sleep(500);
		System.out.println("runnable is done:"+future.isDone());
		Thread.sleep(500);
		System.out.println("runnable is done:"+future.isDone());
		Thread.sleep(500);
		System.out.println("runnable is done:"+future.isDone());
	}


	//Callable + Future
	private static void test2() throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Future<String> future = pool.submit(new MyCallable());
		System.out.println("结果:"+future.get());
		//future.get(5, TimeUnit.SECONDS);尝试获取5秒
		pool.shutdown();
	}
	
	private static void futureMethod1() throws InterruptedException  {
		MyCallable callable = new MyCallable();
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Future<String> future = pool.submit(callable);
		
		Thread.sleep(1000);
		System.out.println("任务是否被取消:"+future.isCancelled());
		//如果为true，表示取消任务时，会发送中断！需要注意的是如果任务正常终止、异常或者取消都将返回true
		boolean ok = future.cancel(false);
		System.out.println("任务是否被取消:"+future.isCancelled());

		System.out.println("是否取消任务成功:"+ok);
	}
	
	//Callable + FutureTask
	@SuppressWarnings("unused")
	private static void test3() throws Exception{
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
		MyCallable callable = new MyCallable();
		FutureTask<String> futureTask = new FutureTask<>(callable);
		pool.submit(futureTask);
		pool.shutdown();
		
		//也可以直接这样
		//new Thread(futureTask).start();
		
		//一秒钟获取结果，拿不到则抛出TimeoutException
		System.out.println("执行结果:"+futureTask.get());
		
		//如果为了可取消性而使用 Future 但又不提供可用的结果，则可以声明 Future<?> 形式类型、并返回 null 作为底层任务的结果。
	}

	@SuppressWarnings("unused")
	private static void test4() throws Exception{
		MyCallable callable = new MyCallable();
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Future<String> future = pool.submit(callable);
		
		
		for(int i=0;i<8;i++){
			if(future.isDone()){
				System.out.println("拿到的结果:"+future.get());
				return ;
			}else{
				System.out.println("线程正在运行");
			}
			Thread.sleep(1000);
		}
	}
}

class MyCallable implements Callable<String>{

	@Override
	public String call(){
		try {
			System.out.println(Thread.currentThread().getName()+" 开始执行");
			Thread.sleep(4000);
			System.out.println(Thread.currentThread().getName()+" 结束执行");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "This is Callable return.";
	}
	
}
