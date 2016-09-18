package com.example.eventbus;

import android.util.Log;
import de.greenrobot.event.EventBus;

public class Student {

	public Student() {
		EventBus.getDefault().register(this);
	}
	
	 public void onEvent(MyEvent event){
			Log.i("Student"+" onEvent", Thread.currentThread().getName()+"---->"+event);
	 }
}
