package com.seek.characterStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class OutputStreamWriterDemo {
	private static final String ABS_NAME = "/Users/luchunyang/DemoSpace/eclipse/JavaIO/readme.txt";

	public static void main(String[] args) throws IOException {

		write();
		read();
	}

	private static void read() throws IOException{
		InputStreamReader isr = new InputStreamReader(new FileInputStream(ABS_NAME));
		char[] buff = new char[200];
		isr.read(buff);
		isr.close();
		System.out.println(new String(buff));
	}

	private static void write() throws IOException{
		// 将字符流转换为字节流。是字符流通向字节流的桥梁。如果不指定字符集编码，该解码过程将使用平台默认的字符编码
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(ABS_NAME));
		osw.write("hello android.");
		osw.write(new char[]{'A','B','C'});
		osw.close();
	}

}
