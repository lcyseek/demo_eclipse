package com.base.file;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAssesFileDemo {

	private static String NAME = "test.txt";
	public static void main(String[] args) throws IOException {
		/**
		 * "r"	以只读方式打开。调用结果对象的任何 write 方法都将导致抛出 IOException。
		 * "rw"	打开以便读取和写入。如果该文件尚不存在，则尝试创建该文件。
		 * "rws"	打开以便读取和写入，对于 "rw"，还要求对文件的内容或元数据的每个更新都同步写入到底层存储设备。
		 * "rwd"	打开以便读取和写入，对于 "rw"，还要求对文件内容的每个更新都同步写入到底层存储设备。
		 */
		RandomAccessFile file = new RandomAccessFile(NAME, "rw");
		//可以直接设置文件的长度！
		file.setLength(2048);
		/*
		file.writeBytes("Hello Android.");
		
		//pos - 从文件开头以字节为单位测量的偏移量位置，在该位置设置文件指针。
		file.seek(100040);
		System.out.println("文件指针位置:"+file.getFilePointer());
		file.write(99);
		
		file.seek(0);
		file.writeChar('K');*/
		file.close();
		
	}

}
