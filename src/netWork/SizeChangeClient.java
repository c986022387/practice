package netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SizeChangeClient {
	public static void main(String[] args) throws IOException {
		System.out.println("�ͻ��������ˣ�");
		Socket s = new Socket("localhost", 10004);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(s.getOutputStream(),true);//�ǵü�true�Զ�ˢ�£�Ҫô�ֶ�ˢ��
		BufferedReader brIn = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
		String text = null;
		while ((text = br.readLine()) != null) {
			pw.println(text);//����print��������Ϊû���У����ͻ��˶�ȡ�����У����������������о��ǲ���ʹ��getbytes������
			System.out.println(brIn.readLine());
			if (text.equals("over")) {
				break;
			}
		}
		s.close();
	}
}
