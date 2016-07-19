package netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendThread implements Runnable{
	
	public void run() {
		// 1.��Ҫ�Ƚ���udp��socket�����߱����ͺͽ��չ��ܡ�
		DatagramSocket ds;
		try {
			ds = new DatagramSocket();
			// 2.�����ݷ�װ�����ݰ��У����ݰ�������DatagramPacket��
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));
			String text = null;
			while ((text = bufferedReader.readLine()) != null) {
				if (text.equals("886")) {
					break;
				}
				byte[] buf = text.getBytes();
				// 3.ʹ��socket�����send���������ݰ����ͳ�ȥ��
				DatagramPacket dp = new DatagramPacket(buf, buf.length,
						InetAddress.getByName("localhost"), 10000);
				ds.send(dp);
			}
			// 4.�ر���Դ
			ds.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
