package com.seek.characterStream;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterDemo {

	private static String FILE = "/Users/luchunyang/DemoSpace/utf8.txt";
	private static String FILE_COPY = "/Users/luchunyang/DemoSpace/utf8_copy.txt";
	
	public static void main(String[] args) throws IOException {

//		copy();
		copy1();
	}

	private static void copy1() throws IOException {
		long start = System.currentTimeMillis();

		BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_COPY));
		BufferedReader br = new BufferedReader(new FileReader(FILE));
		
		char []data = new char[1024];
		int len=0;
		while((len = br.read(data))!= -1){
			bw.write(data, 0, len);
		}
//		bw.newLine();
//		br.readLine();
		
		br.close();
		bw.close();
		long end = System.currentTimeMillis();
		System.out.println("用时:"+(end-start));
	}

	@SuppressWarnings("unused")
	private static void copy() throws IOException{
		long start = System.currentTimeMillis();
		
		FileWriter fw = new FileWriter(FILE_COPY);
		FileReader fr = new FileReader(FILE);
		
		char[] data = new char[1024];
		int len=0;
		while((len = fr.read(data))!= -1){
			fw.write(data, 0, len);
		}
		
		fw.close();
		fr.close();
		
		long end = System.currentTimeMillis();
		System.out.println("用时:"+(end-start));
		
	}

}
