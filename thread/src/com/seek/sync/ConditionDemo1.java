package com.seek.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo1 {

	public static void main(String[] args) {
		
		final OperateBuffer operateBuffer = new OperateBuffer();
		
		//只生产120个数据
		new Thread(new Runnable() {
			public void run() {
				for(int i=1;i<=120;i++){
					operateBuffer.put("["+i+"]");
				}				
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(6000);
					
					while(true){
						System.out.println("开始取数据");
						Object object = operateBuffer.take();
						System.out.println("取出的数据:"+object);
						Thread.sleep(1000);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
	}
}


class OperateBuffer{
	private Lock lock = new ReentrantLock();//锁对象
	private Condition notFull = lock.newCondition();//写线程条件
	private Condition notEmpty = lock.newCondition();//读线程条件
	
	final Object[] items = new Object[100];//缓存队列
	
	int putPtr;//写索引
	int takePtr;//读索引
	int count;//队列中存在的数据个数
	
	public void put(Object o){
		lock.lock();
		try {
			
			if(count == items.length)
				notFull.await();//阻塞写线程
			
			items[putPtr] = o;//写入操作
			++count;
			System.out.println("存入数据:"+o+" 总数为:"+count);
			
			//如果写索引写到了队列的最后一个位置，那么重新设置为0
			if(++putPtr == items.length)
				putPtr = 0;
			
			notEmpty.signalAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public Object take(){
		lock.lock();
		
		try {
			if(count == 0)//队列空
				notEmpty.await();//阻塞读线程
			
			Object object = items[takePtr];
			if(++takePtr == items.length)
				takePtr = 0;
			
			--count;
			
			notFull.signalAll();
			return object;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return null;
	}
	
}

