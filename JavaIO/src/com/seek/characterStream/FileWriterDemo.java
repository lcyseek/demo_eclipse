package com.seek.characterStream;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
	private static final String ABS_NAME = "/Users/luchunyang/DemoSpace/eclipse/JavaIO/readme.txt";

	public static void main(String[] args) throws IOException {
	
		write();
		read();
	}

	private static void read() throws IOException {
		FileReader fr = new FileReader(ABS_NAME);
		char []buff = new char[200];
		fr.read(buff);
		
		//读取length个字节到数组的offset位置处
//		fr.read(buff, 2, 2);

		fr.close();
		System.out.println((int)buff[0]);
		System.out.println("读取的数据:"+new String(buff));
	}

	private static void write() throws IOException {
		FileWriter fw = new FileWriter(ABS_NAME, false);
		
		fw.write("hello world", 1, 4);
		fw.close();
	}
	
	

}
