package com.seek.byteStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ByteArrayOutputStreamDemo {

	public static void main(String[] args) throws IOException {
//		write();
		read();
	}
	
	public static void write() throws IOException{
		//程序内部创建一个byte型别数组的缓冲区.然后利用ByteArrayOutputStream和ByteArrayInputStream的实例向数组中写入或读出byte型数据
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		byte[] data = {66,67,68,69};
		bos.write(70);
		bos.write(data, 1, 2);
		bos.close();
		
		byte[] result = bos.toByteArray();
		for(int i=0;i<result.length;i++){
			System.out.println(result[i]);
		}
	}
	
	public static void read() throws IOException{
		byte[] data = {0,1,2,3,4,5,6,7,8};
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		//ByteArrayInputStream bis = new ByteArrayInputStream(data,1,5);//读取data指定范围内的字节
		
		int ret = -1;
		while((ret=bis.read()) != - 1){
			System.out.println(ret);
		}
		
		
//		byte[] buff = new byte[1024];
//		while((ret = bis.read(buff))!= -1){
//		}
		
		bis.close();

	}

}
