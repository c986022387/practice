package spiltAndMergeFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
/*
 * ��Ƭ���࣬������ܶ��������������ʱ�����ģ����������ڣ����ÿһ��
 * ������ȥ�����������������ʱ�������ȴ洢�������������������������ײ����� 
 * 1����Ҫ������ 
 * 2�������������Ƭ�ļ�������洢�������С�
 * 3������������ȡ���еĶ����ٽ���Ƶ���Ķ�д������
 * 4����ʹ����Ҳ�Ǳ���������ÿһ���������close�ĵ��á�
 * 
 * ��Ȼ�ϲ��ɹ����������£�
 * 1�������ȷ��Ƭ�ĸ�������ȷ��ѭ���Ĵ�����һ��ȷҪ�ж��ٸ�����������
 * 2�����֪���ϲ����ļ������͡�
 * ���������Ӧ�ȶ�ȡ�����ļ���
 * 
 */
public class MergeFile {
	public static void main(String[] args) throws IOException {
		
		
		File partsDir = new File("C:\\Users\\amd\\Desktop\\partsDir");
		mergerFile(partsDir);
	}
	
	
	/**
	 * �ϲ��ļ�
	 * @param partsDir
	 * @throws IOException 
	 */
	public static void mergerFile(File partsDir) throws IOException{
		//1,��ȡ�����ļ���
		File configFile = getConfigFile(partsDir);
		//2,��ȡ�����ļ���Ϣ��������ȡ�����ļ���Ϣ�����Լ���
		Properties properties = getProperties(configFile);
		
		merger(partsDir,properties);
	}

	//���������ļ���ȡ������Ϣ���Լ�
	private static Properties getProperties(File configFile) {
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(configFile);
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}


	//��������Ŀ¼��ȡ�����ļ�����
	private static File getConfigFile(File partsDir) {
		if(!(partsDir.exists()&&partsDir.isDirectory())){
			throw new RuntimeException(partsDir.toString() + "������ЧĿ¼��");
		}
		
		
		//1,�ж���Ƭ�ļ�Ŀ¼���Ƿ����properties�ļ���ʹ�ù�������ɡ�
		File[] files = partsDir.listFiles(new FilenameFilter(){

			public boolean accept(File dir, String name) {
				return name.endsWith(".properties");
			}
		});
		
//		for(File file : files){
//			System.out.println(file.toString());
//		}
		
		if(files.length!=1){
			throw new RuntimeException(partsDir.toString() + "��չ�����ļ������ڻ�Ψһ");
		}
		return files[0];
	}


	
	
	private static void merger(File partsDir, Properties properties) throws FileNotFoundException,
			IOException {
		
		String filename = properties.getProperty("filename");
		int partcount = Integer.parseInt(properties.getProperty("partcount"));
		
		/*List<FileInputStream> list = new ArrayList<FileInputStream>();
		for (int i = 1; i <= 7; i++) {
			list.add(new FileInputStream("C:\\Users\\amd\\Desktop\\partsDir\\"
					+ i + ".part"));
		}
		FileOutputStream fos = new FileOutputStream("C:\\Users\\amd\\Desktop\\partsDir\\2.mp3");
		byte[] buf = new byte[1024*1024];
		//�������ϣ���ȡ������
		for(FileInputStream fis : list){
			int len = fis.read(buf);
			fos.write(buf, 0, len);
			fis.close();
		}
		fos.close();*/
		
		//������
		List<FileInputStream> list = new ArrayList<FileInputStream>();
		for(int i=1;i<partcount;i++){
			list.add(new FileInputStream(new File(partsDir,i+".part")));
		}
		
		//����Collection�������ȡEnumeration����
		Enumeration<FileInputStream> en = Collections.enumeration(list);
		
		//Դ
		SequenceInputStream sis = new SequenceInputStream(en);
		
		//Ŀ��
		FileOutputStream fos = new FileOutputStream(new File(partsDir,filename));
		
		int len = 0;
		byte[] buf = new byte[4*1024];
		while((len=sis.read(buf))!=-1){
			fos.write(buf,0,len);
		}
		sis.close();
		fos.close();
	}
}

