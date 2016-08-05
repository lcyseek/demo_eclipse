package com.example.androidunittest.test;

import com.example.androidunittest.MainActivity;
import com.example.androidunittest.R;

import android.content.Intent;
import android.os.SystemClock;
import android.test.InstrumentationTestCase;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class SimpleTest extends InstrumentationTestCase{
	
	private MainActivity activity;
	private Button btn;
	private TextView tv;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
		Intent intent = new Intent();
		intent.setClassName("com.example.androidunittest", MainActivity.class.getName());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		activity = (MainActivity) getInstrumentation().startActivitySync(intent);
		tv = (TextView) activity.findViewById(R.id.tv);
		btn = (Button) activity.findViewById(R.id.btn);
	}
	
	//资源的回收 垃圾清理
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		activity.finish();
	}
	
	public void testActivity()throws Exception{
		Log.i("test", "test the activity");
		SystemClock.sleep(1500);
		getInstrumentation().runOnMainSync(new PerforClick(btn));
		SystemClock.sleep(3000);
		assertEquals("hello android", tv.getText().toString());
	}
	
	
	private class PerforClick implements Runnable{
		private Button b;
		
		public PerforClick(Button b) {
			super();
			this.b = b;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			b.performClick();
		}
	};

	
	//测试其他方法
	public void testAdd(){
		Log.i("test", "test add method");
		int ret = activity.addd(11, 21);
		assertEquals(32, ret);
	}
}
