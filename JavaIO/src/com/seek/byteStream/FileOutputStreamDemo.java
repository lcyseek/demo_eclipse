package com.seek.byteStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {
	
	private static final String ABS_NAME = "/Users/luchunyang/DemoSpace/eclipse/JavaIO/readme.txt";

	public static void main(String[] args) throws IOException {
		write();
//		read();
		read1();
//		read2();
	}
	
	public static void write() throws IOException{
		//FileOutputStream fos = new FileOutputStream(ABS_NAME);
		FileOutputStream fos = new FileOutputStream(ABS_NAME,true);//追加模式

		byte [] b = {97,98,99,100};
		fos.write(101);
		fos.write(b, 1, 2);
		fos.close();
	}

	//一次读取一个字节(0~255).如果读取的含有中文 则乱码。一个汉字2个字节
	public static void read() throws IOException{
		FileInputStream fis = new FileInputStream(ABS_NAME);
		int data;
		while((data = fis.read())!= -1){
			System.out.print((char)data);
		}
		System.out.println("");
		fis.close();
	}
	
	// 从输入流中读取一定数量的字节，并将其存储在缓冲区数组 b 中。以整数形式返回实际读取的字节数
	public static void read1() throws IOException{
		FileInputStream fis = new FileInputStream(ABS_NAME);
		
		byte [] data = new byte[1024];
		int len = 0;
		while((len = fis.read(data)) != -1){
			System.out.println("len="+len);
			System.out.println(new String(data));
		}
		fis.close();
	}
	
	public static void read2() throws IOException{
		FileInputStream fis = new FileInputStream(ABS_NAME);
		
		System.out.println(fis.available());
		byte [] data = new byte[fis.available()];
		int len = 0;//表示成功读取的字节数的个数
		
		//从fis中批量读取字节，放入到data里，从offset位置开始放，最多放length个字节
		while(len < fis.available()){
			fis.read(data,len,fis.available() - len);
		}
		System.out.println(new String(data));
		fis.close();
		
	}
}
