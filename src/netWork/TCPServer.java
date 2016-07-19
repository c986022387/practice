package netWork;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) throws IOException {
		System.out.println("服务端端启动。。。");
		ServerSocket ss = new ServerSocket(10000);
		
		Socket s = ss.accept();
		
		InputStream is = s.getInputStream();
		byte[] buf = new byte[1024];
		int len = is.read(buf);
		String text = new String(buf,0,len);
		System.out.println(s.getInetAddress().getHostAddress()+":"+s.getPort()+" "+ text);
		
		OutputStream os = s.getOutputStream();
		os.write("服务端收到啰".getBytes());
		
		s.close();
		ss.close();
		
	}
}	
