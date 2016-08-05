package com.seek.characterStream;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;

public class CharArrayWriterDemo {

	public static void main(String[] args) throws IOException {

		CharArrayWriter cArrayWriter = new CharArrayWriter();
		cArrayWriter.write("hello android.");
		cArrayWriter.write(new char[]{'A','B'});
		cArrayWriter.close();
		
		System.out.println(cArrayWriter.toCharArray());	
		
		char[] buff = new char[200];
		CharArrayReader cArrayReader = new CharArrayReader(cArrayWriter.toCharArray());
		cArrayReader.read(buff);
		System.out.println(new String(buff));

	}



}
