package com.example.eventbus;

public class MyEvent {
	String mString;

	public MyEvent(String mString) {
		super();
		this.mString = mString;
	}

	@Override
	public String toString() {
		return "MyEvent [mString=" + mString + "]";
	}

}
