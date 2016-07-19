package netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SizeChangeServer {
	public static void main(String[] args) throws IOException {
		System.out.println("服务端运行了！");
		ServerSocket ss = new ServerSocket(10004);
		while (true) {
			Socket s = ss.accept();
			PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
			BufferedReader brIn = new BufferedReader(new InputStreamReader(
					s.getInputStream()));
			String text = null;
			while ((text = brIn.readLine()) != null) {
				if("over".equals(text)){
					break;
				}
				System.out.println(s.getInetAddress().getHostAddress() + ":"
						+ s.getPort() + "-->" + text);//不用print方法，因为没换行，而客户端读取的整行，会引起阻塞。还有就是不用使用getbytes方法了
				pw.println(text.toUpperCase());
			}
			s.close();
		}
	}
}
