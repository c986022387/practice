package netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SizeChangeServer {
	public static void main(String[] args) throws IOException {
		System.out.println("����������ˣ�");
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
						+ s.getPort() + "-->" + text);//����print��������Ϊû���У����ͻ��˶�ȡ�����У����������������о��ǲ���ʹ��getbytes������
				pw.println(text.toUpperCase());
			}
			s.close();
		}
	}
}
