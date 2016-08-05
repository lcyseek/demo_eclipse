package com.seek.byteStream.filterstream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamDemo {

	private static String FILE = "/Users/luchunyang/DemoSpace/qudong.zip";
	private static String FILE_COPY = "/Users/luchunyang/DemoSpace/qudong_copy.zip";

	
	public static void main(String[] args) throws IOException {
		copy();
//		copy1();
	}

	private static void copy1() throws IOException {
		long start=System.currentTimeMillis();
		FileOutputStream fos = new FileOutputStream(FILE_COPY);
		FileInputStream fis = new FileInputStream(FILE);
		
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		
		byte[] data = new byte[1024];
		int len;
		while((len = bis.read(data)) != -1){
			bos.write(data, 0, len);
		}
		
		bis.close();
		bos.close();
		long end=System.currentTimeMillis();
		System.out.println(end - start);
	}

	private static void copy() throws IOException {
		long start=System.currentTimeMillis();

		FileOutputStream fos = new FileOutputStream(FILE_COPY);
		FileInputStream fis = new FileInputStream(FILE);
		byte[] data = new byte[1024];
		
		int len=0;
		while((len = fis.read(data)) != -1){
			fos.write(data, 0, len);
		}
		
		fis.close();
		fos.close();
		
		long end=System.currentTimeMillis();
		System.out.println(end - start);
	}

	
	

}
