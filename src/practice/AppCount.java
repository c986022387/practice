package practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AppCount {

	public static void main(String[] args) throws IOException {
		if(isRun()){
			throw new RuntimeException("ʹ�����ѵ����빺��");
		}
		runcode();
	}

	private static boolean isRun() throws IOException {
		File file = new File("C:\\Users\\amd\\Desktop\\appcount.properties");
		if(!file.exists()){
			file.createNewFile();
			
		}
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(file);
//		FileOutputStream fos = new FileOutputStream(file);//���ܷ��������ΪԴ�ļ��������Ѿ�������
		prop.load(fis);
		String value = prop.getProperty("count");
		int count = 0;
		if(value!=null){
			count = Integer.parseInt(value);
		}
		count++;
		if(count>5){
			return true;
		}
		prop.setProperty("count",Integer.toString(count));
		FileOutputStream fos = new FileOutputStream(file);
		prop.store(fos, "app count");
		fis.close();
		fos.close();
		return false;
	}

	private static void runcode() {
		System.out.println("app run...");
	}

}
