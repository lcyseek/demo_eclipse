package com.seek.characterStream;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * PrintWriter提供了PrintStream的所有打印方法
 * 与PrintStream的区别：作为处理流使用时，PrintStream只能封装OutputStream类型的字节流，而PrintWriter既可以封装OutputStream类型的字节流，还能够封装Writer类型的字符输出流并增强其功能。
 * @author luchunyang
 *
 */
public class PrintWriterDemo {
	
	private static final String ABS_NAME = "/Users/luchunyang/DemoSpace/eclipse/JavaIO/readme.txt";

	public static void main(String[] args) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(ABS_NAME);
		pw.write("hello android.");
		pw.write(new char[]{'A','B'});
		pw.close();
	}

}
