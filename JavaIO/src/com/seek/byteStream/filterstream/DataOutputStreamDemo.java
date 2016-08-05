package com.seek.byteStream.filterstream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 有时没有必要存储整个对象的信息，而只是要存储一个对象的成员数据，成员数据的类型假设都是Java的基本数据类型，
 * 这样的需求不必使用到与Object输入、输出相关的流对象，可以使用DataInputStream、DataOutputStream来写入或读出数据
 * 
 * DataOutputStream和ObjectOutputStream在处理基本类型的时候没有什么很大的区别，主要区别是：ObjectOutputStream可以将一个实现了序列化的类实例写入到输出流中，
 * ObjectInputStream可以从输入流中将ObjectOutputStream输出的类实例读入到一个实例中。DataOutputStream只能处理基本类型
 */

public class DataOutputStreamDemo {
	private static final String ABS_NAME = "/Users/luchunyang/DemoSpace/eclipse/JavaIO/readme.txt";

	public static void main(String[] args) throws IOException {
		write();
		read();
	}

	private static void read() throws IOException{
		FileInputStream fis = new FileInputStream(ABS_NAME);
		DataInputStream dis = new DataInputStream(fis);
		
		System.out.println(dis.readInt());
		System.out.println(dis.readBoolean());
		System.out.println(dis.readFloat());
		System.out.println(dis.readChar());
		
		fis.close();
		dis.close();
	}

	private static void write() throws IOException{
		FileOutputStream fos = new FileOutputStream(ABS_NAME);
		DataOutputStream dos = new DataOutputStream(fos);
		
		dos.writeInt(11);
		dos.writeBoolean(false);
		dos.writeFloat(11.2f);
		dos.writeChar('A');
		fos.close();
		dos.close();
	}

}
