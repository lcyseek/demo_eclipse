package com.seek.byteStream.filterstream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * PrintStream在OutputStream基础之上提供了增强的功能，即可以方便地输出各种类型的数据（而不仅限于byte型）的格式化表示形式
 * System.out就是打印流。
 */
public class PrintStreamDemo {
	
	private static final String ABS_NAME = "/Users/luchunyang/DemoSpace/eclipse/JavaIO/readme.txt";

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		write();
	}

	private static void write() throws FileNotFoundException, InterruptedException {
	
		FileOutputStream fos = new FileOutputStream(ABS_NAME);
		PrintStream ps = new PrintStream(fos);
		ps.print("hello android.");
		ps.print(21.5f);
		ps.println("打印完此行后换行");
		ps.println(false);
		ps.format("name is %s,age is %d\n", "seek陆",22);
		//把string的指定位置插入到流里.
		ps.append("insert", 1, 3);
		Thread.sleep(30000);
		ps.close();
	}

}
