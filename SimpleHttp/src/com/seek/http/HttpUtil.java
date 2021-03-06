package com.seek.http;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpUtil {
	
	public static void get(String urlString) throws Exception {
		
		URL url = new URL(urlString);
	
		//URL.openConnection():只创建URLConnection或者HttPURLConnection实例，但是并不进行真正的连接操作
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		//设置连接主机超时（单位：毫秒）.这个时间只是指建立socket的时间，而并不是指发送数据以及数据传输的时间。所以在一般的连接处理中，这个时间已经是非常地长了
        connection.setConnectTimeout(3000);
        
        //设置从主机读取数据超时（单位：毫秒）
        connection.setReadTimeout(3000);
        
        //设置请求头属性.在协议里可以看到
        connection.setRequestProperty("custom", "luchunyang@zhilai.com");
        
        //实际上只是建立了一个与服务器的tcp连接，并没有实际发送http请求.一般不需要调用
		connection.connect();
		
		//获取返回码
		int retCode = connection.getResponseCode();
		
		System.out.println("retCode="+retCode);
		
		
		//Content-length：用于描述HTTP消息实体的传输长度 
		//Transfer-Encoding=[chunked]	分块传输编码:允许HTTP由网页服务器发送给客户端应用的数据可以分成多个部分
		//Content-Length如果存在并且有效的话，则必须和消息内容的传输长度完全一致
		//如果存在Transfer-Encoding（重点是chunked），则在header中不能有Content-Length，有也会被忽视
		//说明：通常，HTTP应答消息中发送的数据是整个发送的，Content-Length消息头字段表示数据的长度。数据的长度很重要，因为客户端需要知道哪里是应答消息的结束，以及后续应答消息的开始。然而，使用分块传输编码，数据分解成一系列数据块，并以一个或多个块发送，这样服务器可以发送数据而不需要预先知道发送内容的总大小。
		int length = connection.getContentLength();
		System.out.println("ContentLength="+length);
		
		
		if(retCode != HttpURLConnection.HTTP_OK){
			throw new Exception("HTTP Request is not success, Response code is " + retCode);
		}
		
		
		//获取响应头内容
		Map<String, List<String>> headers = connection.getHeaderFields();
		for(String key:headers.keySet()){
			System.out.println(key+"="+headers.get(key));
		}
		
		//getInputStream之类属于应用层的操作，都会调用connect操作
		InputStream is = connection.getInputStream();

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	
		String result = "";
		String line;
		while((line = reader.readLine()) != null){
			result += line + "\r\n";
		}
		
		//关闭流
		is.close();
	
		System.out.println(result);
		
		connection.disconnect();
		
	}
	
	public static void getImage(String urlString) throws Exception {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		
	
		InputStream is = connection.getInputStream();
		int length = connection.getContentLength();
		System.out.println("ContentLength="+length);
		
		FileOutputStream fos = new FileOutputStream("abc.png");
		byte[] data = new byte[1024];
		int len=0;
		while( (len = is.read(data)) != -1){
			fos.write(data,0,len);
		}
		
		is.close();
		fos.close();
		
		connection.disconnect();
	}
	
	public static void getMp3Info(String mp3) throws IOException{
		URL url = new URL("http://mobilecdn.kugou.com/api/v3/search/song?keyword="+URLEncoder.encode("心太软", "utf-8"));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("contentType","utf-8");
		
		String info = "";
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		
		while( (line = reader.readLine()) != null){
			info += line+"\r\n";
		}
		
		reader.close();
		connection.disconnect();
		
		System.out.println(info);
	}
	
	public static void post(String urlString)throws Exception{
		
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(1000);
		connection.setRequestMethod("POST");
		
		//Post 请求不能使用缓存   
		connection.setUseCaches(false);
		
		//设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在   
		//http正文内，因此需要设为true, 默认情况下是false; 
		connection.setDoOutput(true);
		
        connection.setRequestProperty("Charset", "UTF-8");
       
        //表单提交
        String data = "name="+URLEncoder.encode("柳岩","UTF-8");
        OutputStream os = connection.getOutputStream();
        
        //向对象输出流写出数据，这些数据将存到内存缓冲区中   
        os.write(data.getBytes());
        os.flush();
        
        //关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,
        //在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
        os.close();
        
        
        //循环之后才会提交http!
        int i=0;
        while(true){
        	if(++i == 6){
        		break;
        	}
        	Thread.sleep(1000);
        }
        
        //注意，实际发送请求的代码段就在这里  (connection.getInputStream();或者connection.getResponseCode()) 
        //无论是post还是get，http请求实际上直到HttpURLConnection的getInputStream()或者connection.getResponseCode()这个函数里面才正式发送出去。 
        int retCode = connection.getResponseCode();
        System.out.println("retCode="+retCode);
       

	}
}
