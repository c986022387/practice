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
 * 碎片过多，会产生很多的输入流对象，这时正常的，不正常在于，面对每一个
 * 流对象去操作。当流对象过多时，必须先存储起来，面对流对象的容器更容易操作。 
 * 1，需要容器。 
 * 2，将流对象和碎片文件关联后存储到容器中。
 * 3，遍历容器获取其中的对象，再进行频繁的读写操作。
 * 4，即使关流也是遍历容器对每一个对象进行close的调用。
 * 
 * 虽然合并成功，问题如下：
 * 1，如何明确碎片的个数，来确定循环的次数，一明确要有多少个输入流对象。
 * 2，如何知道合并的文件的类型。
 * 解决方案：应先读取配置文件。
 * 
 */
public class MergeFile {
	public static void main(String[] args) throws IOException {
		
		
		File partsDir = new File("C:\\Users\\amd\\Desktop\\partsDir");
		mergerFile(partsDir);
	}
	
	
	/**
	 * 合并文件
	 * @param partsDir
	 * @throws IOException 
	 */
	public static void mergerFile(File partsDir) throws IOException{
		//1,获取配置文件。
		File configFile = getConfigFile(partsDir);
		//2,获取配置文件信息容器，获取配置文件信息的属性集。
		Properties properties = getProperties(configFile);
		
		merger(partsDir,properties);
	}

	//根据配置文件获取配置信息属性集
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


	//根据配置目录获取配置文件对象
	private static File getConfigFile(File partsDir) {
		if(!(partsDir.exists()&&partsDir.isDirectory())){
			throw new RuntimeException(partsDir.toString() + "不是有效目录！");
		}
		
		
		//1,判断碎片文件目录中是否存在properties文件。使用过滤器完成。
		File[] files = partsDir.listFiles(new FilenameFilter(){

			public boolean accept(File dir, String name) {
				return name.endsWith(".properties");
			}
		});
		
//		for(File file : files){
//			System.out.println(file.toString());
//		}
		
		if(files.length!=1){
			throw new RuntimeException(partsDir.toString() + "扩展名的文件不存在或不唯一");
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
		//遍历集合，获取流对象。
		for(FileInputStream fis : list){
			int len = fis.read(buf);
			fos.write(buf, 0, len);
			fis.close();
		}
		fos.close();*/
		
		//升级版
		List<FileInputStream> list = new ArrayList<FileInputStream>();
		for(int i=1;i<partcount;i++){
			list.add(new FileInputStream(new File(partsDir,i+".part")));
		}
		
		//利用Collection工具类获取Enumeration对象
		Enumeration<FileInputStream> en = Collections.enumeration(list);
		
		//源
		SequenceInputStream sis = new SequenceInputStream(en);
		
		//目的
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

