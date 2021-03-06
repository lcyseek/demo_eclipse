package com.seek.collection.queue;

import java.util.concurrent.TimeUnit;

public class TimeUtilDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeUnit timeUnil = TimeUnit.DAYS;
		
		System.out.println(timeUnil.name());
		System.out.println(timeUnil.toDays(1));
		System.out.println(timeUnil.toHours(2));
		System.out.println(timeUnil.toMinutes(1));
		System.out.println(timeUnil.toSeconds(1));
		
		System.out.println("1 天有"+timeUnil.convert(1, timeUnil.DAYS)+timeUnil.name());
		System.out.println("24小时有"+timeUnil.convert(24, timeUnil.HOURS)+timeUnil.name());
		System.out.println("86400秒有"+timeUnil.convert(86400, timeUnil.SECONDS)+timeUnil.name());
		
		System.out.println("-------------------------");
		
		timeUnil = timeUnil.MINUTES;
		System.out.println(timeUnil.toDays(1440));
		System.out.println(timeUnil.toSeconds(3));
		
	}

}
