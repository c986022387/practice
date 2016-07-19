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
		 * 配置文件规律，只要读取一行文本，按照=对文本进行切割即可。
		 */
		BufferedReader bufr = new BufferedReader(new FileReader(configFile));
		String line = null;
		while((line=bufr.readLine())!=null){
			String[] arr = line.split("=");
			System.out.println(arr[0]+"::::"+arr[1]);
		}
		/*
		 * 发现配置文件信息很多，需要进行存储。
		 * 用哪个容器呢？个数不确定，就是用集合。
		 * 发现信息中存在对应关系，使用map集合。
		 * 发现一点配置文件中的信息都是字符串，这些信息不在内存中而是在键盘上。
		 * map中和io技术结合的集合对象：Preperties
		 */
		bufr.close();
	}

}
