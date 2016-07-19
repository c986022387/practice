package spiltAndMergeFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SplitFileTest {
	private static final int BUFFER_SIZE = 1048576;//1024*1024
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");

	public static void main(String[] args) {
		//��ϰ����һ��ý���ļ��и�ɶ����Ƭ��
		/*
		 * ˼·��
		 * 1����ȡԴ�ļ�����Դ�ļ������ݷֱ��Ƶ�����ļ��С�
		 * 2���иʽ�����֣�������Ƭ�����У�Ҫô����ָ����С�С�
		 * 3��һ����������Ӧ����������
		 * 4��ÿһ����Ƭ����Ҫ��ţ�˳��Ҫ��
		 */
		File srcFile = new File("C:\\Users\\amd\\Desktop\\�ζ�Ұ - ��������.mp3");
		File partsDir = new File("C:\\Users\\amd\\Desktop\\partsDir");
		try {
			spiltFile(srcFile,partsDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �и��ļ������ҽ�������Ϣ�洢���и��ļ���ĩ��
	 * 
	 * @param srcFile
	 * @param partsDir
	 * @throws IOException
	 */
	private static void MyspiltFile(File srcFile, File partsDir) throws IOException {
		
	}
	
	/**
	 * �и��ļ���������������Ϣ
	 * @param srcFile
	 * @param partsDir
	 * @throws IOException 
	 */
	private static void spiltFile(File srcFile, File partsDir) throws IOException {
		//��׳�Ե��ж�
		if(!(srcFile.exists() && srcFile.isFile())){
			throw new RuntimeException("Դ�ļ�������ȷ���ļ����ļ�������");
		}
		if(!partsDir.exists()){
			partsDir.mkdirs();
		}
		//1,ʹ���ֽ�����ȡ����Դ�ļ�������
		FileInputStream fis = new FileInputStream(srcFile);
		//2,��ȷĿ�ġ�Ŀ�����Դ�ж����ֻ�������á�
		FileOutputStream fos = null;
		//3,���建������1M
		byte[] buf = new byte[BUFFER_SIZE];
		//4,Ƶ����д������
		int len = 0;
		int count = 1;
		while((len = fis.read(buf))!=-1){
			//�������������ֻ�������˻�������С����Ƭ����ȷ����ֱ������Ƭ�ļ���д���ݡ�
			//�����ļ��洢��partsDir�У�����Ϊ���+part��չ����
			fos = new FileOutputStream(new File(partsDir,(count++)+".part"));
			//���������е�����д�뵽��Ƭ�ļ��С�
			fos.write(buf, 0, len);
			//ֱ�ӹر��������
			fos.close();
		}
		/*
		 * ��Դ�ļ��Լ��µ�һЩ��ϢҲ��������������Ƭ�ļ�һ���͡�
		 * ��Ϣ��
		 * 1��Դ�ļ������ƣ��ļ����ͣ�
		 * 2���и����Ƭ�ĸ�����
		 * ����Щ��Ϣ������װ��һ���ļ��С�
		 * ��Ҫһ���������ɴ˶�����
		 */
		String  filename = srcFile.getName();
		int partCount = count;
		//����һ���������
		fos = new FileOutputStream(new File(partsDir,count+".properties"));
		//����һ�����Լ���
		Properties prop = new Properties();
		//��������Ϣ�洢�������С�
		prop.setProperty("filename", srcFile.getName());
		prop.setProperty("partcount", Integer.toString(partCount));
		//�����Լ��е���Ϣ�־û���
		prop.store(fos, "part file info");
//		fos.write(("filename="+filename+LINE_SEPARATOR).getBytes());
//		fos.write(("partCount="+partCount).getBytes());
		fos.close();
		fis.close();
	}
}
