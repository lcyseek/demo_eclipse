package com.seek.interrupt;

import java.net.ServerSocket;

public class SocketThread extends Thread{
	public static ServerSocket socket ;

	@Override
	public void run() {
		
		try {
			socket = new ServerSocket(30000);
			while(true){
				System.out.println("start recv socket");
				socket.accept();
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("isInterrupted:"+isInterrupted());
		}
	}
}
