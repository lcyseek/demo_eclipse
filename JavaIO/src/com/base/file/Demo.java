package com.base.file;

import java.io.File;
import java.io.IOException;

public class Demo {
	private static final String ABS_NAME = "/Users/luchunyang/DemoSpace/eclipse/JavaIO/readme.txt";
	private static final String NAME = "readme.txt";
	private static final String DIR_NAME="/Users/luchunyang/DemoSpace/eclipse/JavaIO/abc/test";
	private static final String DIR_NAME1="/Users/luchunyang/DemoSpace/eclipse/JavaIO/abc1/test1/abc.txt";

	public static void main(String[] args) throws IOException, InterruptedException {
		
		File file = new File(NAME);
		if(!file.exists()){
			System.out.println(file.getName()+" not exist.");
			file.createNewFile();
		}else{
			long length = file.length();
			File parent = file.getParentFile();//上一级的目录名字
			String path = file.getAbsolutePath();//绝对路径
			
			System.out.println("length="+length);
			if(parent != null)
				System.out.println("parent="+parent.getName());
			System.out.println("absolutePath="+path);
			System.out.println("path="+file.getPath());
			
			//第一个参数是命名前缀,必须至少是三字符长.
			//第二个参数是后缀
			//第三个参数是文件创建的目录
			File temp = File.createTempFile("Seek", ".txt", new File("/Users/luchunyang/DemoSpace/eclipse/JavaIO/"));
			System.out.println("创建的临时文件为:"+temp.getName());//Seek694316377886202713.txt
			
			Thread.sleep(5000);
			//退出时删除文件。任何文件对象都可以使用此方法!!!!
			temp.deleteOnExit();
			
			//按字母顺序比较两个抽象路径名
			int ret = file.compareTo(new File(NAME));
			System.out.println(ret);
			
			//是否是绝对路径文件
			System.out.println("isAbsolute:"+file.isAbsolute());
			System.out.println("isAbsolute:"+new File(ABS_NAME).isAbsolute());
		}
		
		File dir = new File(DIR_NAME);
		if(dir.isDirectory())
			System.out.println(DIR_NAME+" is a dir.");
		
		boolean bool = dir.mkdir();
		System.out.println("mkdir:"+bool);
		
		bool = dir.mkdirs();
		System.out.println("mkdirs:"+bool);

		//先创建多级目录，然后再创建文件
		File op = new File(DIR_NAME1);
		new File(op.getParentFile().getAbsolutePath()).mkdirs();
		file.createNewFile();
		
		System.out.println("--------------------------------------");
		//目录下的所有目录结构 listFiles能够获取当前文件夹下的所有文件和文件夹
		File [] lists = new File("/").listFiles();
		for (int i = 0; i < lists.length; i++) {
			System.out.println(lists[i].getName());
		}
	}

}
