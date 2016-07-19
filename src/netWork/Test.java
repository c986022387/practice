package netWork;

public class Test {
	public static void main(String[] args) {
		SendThread sendThread = new SendThread();
		ReceiveThread receiveThread = new ReceiveThread();
		Thread thread1 = new Thread(sendThread);
		Thread thread2 = new Thread(receiveThread);
		thread1.start();
		thread2.start();
	}
}
