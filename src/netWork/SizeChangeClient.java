package netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SizeChangeClient {
	public static void main(String[] args) throws IOException {
		System.out.println("客户端运行了！");
		Socket s = new Socket("localhost", 10004);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(s.getOutputStream(),true);//记得加true自动刷新，要么手动刷新
		BufferedReader brIn = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String text = null;
		while ((text = br.readLine()) != null) {
			pw.println(text);//不用print方法，因为没换行，而客户端读取的整行，会引起阻塞。还有就是不用使用getbytes方法了
			System.out.println(brIn.readLine());
			if (text.equals("over")) {
				break;
			}
		}
		s.close();
	}
}
