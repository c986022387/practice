package netWork;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
	public static void main(String[] args) throws  IOException {
		System.out.println("客户端启动。。。。");
		
		Socket socket = new Socket("localhost",10000);
		
		OutputStream out = socket.getOutputStream();
		out.write("客户端".getBytes());
		
		InputStream inputStream = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = inputStream.read(buf);
		String text = new String(buf,0,len);
		System.out.println(text);
		
		socket.close();
	}
}
