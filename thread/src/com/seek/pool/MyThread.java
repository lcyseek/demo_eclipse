package com.seek.pool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread{
	private String name;
	private int sleep;
	
	public MyThread(String name) {
		this(name,3000);
	}
	
	public MyThread(String name,int sleep) {
		super(name);
		this.name = name;
		this.sleep = sleep;
	}
	
//	public String getSleep(){
//		return name;
//	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(df.format(new Date())+" "+"("+Thread.currentThread().getName()+")"+name+"正在执行...");
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(df.format(new Date()) +" ("+Thread.currentThread().getName()+")"+name+"执行完毕");

		super.run();
	}

}
