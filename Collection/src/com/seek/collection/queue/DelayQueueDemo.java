package com.seek.collection.queue;

import java.util.HashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
当向缓存中添加key-value对时，如果这个key在缓存中存在并且还没有过期，需要用这个key对应的新过期时间
为了能够让DelayQueue将其已保存的key删除，需要重写实现Delayed接口添加到DelayQueue的DelayedItem的hashCode函数和equals函数
当缓存关闭，监控程序也应关闭，因而监控线程应当用守护线程
 *
 */
public class DelayQueueDemo {

	public static void main(String[] args) {

		Cache<String, Integer> cache = new Cache<String, Integer>();
		cache.put("one", 1, 6000);
		cache.put("two", 2, 2000);

		cache.start();
	}

	static class Cache<K, V> {

		public Cache() {
			super();
		}

		public HashMap<K, V> map = new HashMap<>();
		public DelayQueue<DelayedItem<K>> queue = new DelayQueue<>();

		public void put(K k, V v, long liveTime) {
			V v2 = map.put(k, v);
			DelayedItem<K> delayedItem = new DelayedItem<K>(k, liveTime);

			if (v2 != null) {
				// 为什么可以移除？因为DelayedItem实现了 hashCode和Equals方法,可以根据key判断是否相同
				queue.remove(delayedItem);
			}
			queue.put(delayedItem);
		}

		public void start() {
			while (true) {
				DelayedItem<K> item = queue.poll();
				if (item != null){
					map.remove(item.getT());
					System.out.println("k=" + item.getT()+" from cache");
				}
				else
					System.err.println("item is null");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	static class DelayedItem<T> implements Delayed {
		private T t;
		private long liveTime;
		private long removeTime;

		public DelayedItem(T t, long liveTime) {
			super();
			this.t = t;
			this.liveTime = liveTime;
			this.removeTime = liveTime + System.currentTimeMillis();
		}

		@Override
		public int compareTo(Delayed o) {
			// TODO Auto-generated method stub

			if (o == null)
				return 1;
			if (o == this)
				return 0;

			@SuppressWarnings("unchecked")
			DelayedItem<T> delayedItem = (DelayedItem<T>) o;
			if (liveTime > delayedItem.liveTime)
				return 1;
			else if (liveTime == delayedItem.liveTime)
				return 0;
			else
				return -1;
		}

		@Override
		public long getDelay(TimeUnit unit) {
			// TODO Auto-generated method stub
			return unit.convert(removeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}

		public T getT() {
			return t;
		}

		public void setT(T t) {
			this.t = t;
		}

		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return t.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			if (obj instanceof DelayedItem) {
				return obj.hashCode() == hashCode() ? true : false;
			}

			return false;
		}

	}
}
