package spiltAndMergeFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReaderPartConfigure {

	public static void main(String[] args) {
		File configFile = new File("C:\\Users\\amd\\Desktop\\partsDir\\11.partconfig");
		try {
			readPathConfig(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void readPathConfig(File configFile)
			throws IOException {
		/*
		 * �����ļ����ɣ�ֻҪ��ȡһ���ı�������=���ı������и�ɡ�
		 */
		BufferedReader bufr = new BufferedReader(new FileReader(configFile));
		String line = null;
		while((line=bufr.readLine())!=null){
			String[] arr = line.split("=");
			System.out.println(arr[0]+"::::"+arr[1]);
		}
		/*
		 * ���������ļ���Ϣ�ܶ࣬��Ҫ���д洢��
		 * ���ĸ������أ�������ȷ���������ü��ϡ�
		 * ������Ϣ�д��ڶ�Ӧ��ϵ��ʹ��map���ϡ�
		 * ����һ�������ļ��е���Ϣ�����ַ�������Щ��Ϣ�����ڴ��ж����ڼ����ϡ�
		 * map�к�io������ϵļ��϶���Preperties
		 */
		bufr.close();
	}

}
