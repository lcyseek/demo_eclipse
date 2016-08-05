package com.seek.characterStream;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class StringWriterDemo {

	public static void main(String[] args) throws IOException {
		
		StringWriter sw = new StringWriter();
		sw.write("hello android");
		sw.write(new char[]{'A','B','C'});
		
		System.out.println(sw.toString());
		
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");  
        sb.append("<user>");  
        sb.append("<age>19</age>");  
        sb.append("<name>yang</name>");  
        sb.append("</user>");  
		StringReader sr = new StringReader(sb.toString());
		
		char[] data = new char[200];
		sr.read(data);
		System.out.println(new String(data));
	}

}
