package netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendThread implements Runnable{
	
	public void run() {
		// 1.需要先建立udp的socket。它具备发送和接收功能。
		DatagramSocket ds;
		try {
			ds = new DatagramSocket();
			// 2.将数据封装到数据包中，数据包对象是DatagramPacket。
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(System.in));
			String text = null;
			while ((text = bufferedReader.readLine()) != null) {
				if (text.equals("886")) {
					break;
				}
				byte[] buf = text.getBytes();
				// 3.使用socket对象的send方法将数据包发送出去。
				DatagramPacket dp = new DatagramPacket(buf, buf.length,
						InetAddress.getByName("localhost"), 10000);
				ds.send(dp);
			}
			// 4.关闭资源
			ds.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
