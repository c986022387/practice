package netWork;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceiveThread implements Runnable {

	public void run() {

		try {
			// 1.����udpsocket����
			DatagramSocket datagramSocket = new DatagramSocket(10000);
			while (true) {
				// 2.�������ݣ�֮ǰ�Ƚ����ݴ洢�����ݰ��С�
				// 3.�ȶ������ݰ�
				byte[] buf = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				datagramSocket.receive(dp);// ����
				// 4.ͨ�����ݰ������ȡ���ݰ������ݣ����Ͷ˵�ip�����Ͷ˵Ķ˿ڣ����͹��������ݡ�
				String ip = dp.getAddress().getHostAddress();
				int port = dp.getPort();
				String text = new String(dp.getData(), 0, dp.getLength());
				System.out.println(ip + ":" + port + text);

			}
//			// 5.�ر���Դ��
//			datagramSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
