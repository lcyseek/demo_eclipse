package com.example.androidunittest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 
 * android的测试方法主要有AndroidTestCase和InstrumentationTestCase。
 * 
 * InstrumentationTestCase 没有图形界面的，具有启动能力的，用于监控其他类的工具类
 *
 */
public class MainActivity extends Activity {

	private Button btn;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn = (Button) findViewById(R.id.btn);
		tv = (TextView) findViewById(R.id.tv);
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv.setText("hello android");
			}
		});
	}
	
	public int addd(int a,int b){
		return a+b;
	}
}
