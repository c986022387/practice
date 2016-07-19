package practice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

	
	
public class IOandFile{
	
	public static void myTry(File destFile) throws IOException{
		FileOutputStream fos = new FileOutputStream(destFile);
		fos.write("你好".getBytes());
		fos.close();
	}
	
	/**
	 * 删除整个文件夹
	 * @param destfile
	 */
	public static void delete(File destfile){
		if(destfile.isFile()){
			destfile.delete();
			return;
		}
		File[] files = destfile.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				delete(file);
			}
			file.delete();
		}
		destfile.delete();
	}
	
	/**
	 * 获取想要的文件类型
	 * @param destfile
	 * @param filter
	 */
	public static List<String> FindFile(File destfile,MyFileFilter filter,List<String> list){
		
		File[] files = destfile.listFiles();//注意这里不应该传过滤器
		for(File file:files){
			if(file.isDirectory()){
				FindFile(file,filter,list);
			}else{
				if(filter.accept(file)){
					list.add(file.toString());
				}
			}
		}
		return list;
	}
	
	/**
	 * 拷贝文件，字节批量读取。
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void CopyFile(File srcFile, File destFile) throws IOException {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("文件：" + srcFile + "不存在！");
		}
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "不是文件！");
		}
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		byte[] bytes = new byte[2 * 2014];
		int a;
		while ((a = in.read(bytes, 0, bytes.length)) != -1) {
			out.write(bytes, 0, a);
			out.flush();
		}
		in.close();
		out.close();
	}

	/**
	 * 带缓冲的字节流拷贝文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void CopyFileByBuffer(File srcFile, File destFile)
			throws IOException {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("文件" + srcFile + "不存在！");
		}
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "不是文件！");
		}
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(srcFile));
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(destFile));
		int c ;
		while((c = bis.read())!=-1){
			bos.write(c);
			bos.flush();//刷新缓冲区
		}
		bis.close();
		bos.close();
	}

	/**
	 * 单字节拷贝文件
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void CopyFileBybyte(File srcFile, File destFile)
			throws IOException {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("文件：" + srcFile + "不存在！");
		}
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "不是文件！");
		}
		FileInputStream in = new FileInputStream(srcFile);
		FileOutputStream out = new FileOutputStream(destFile);
		int a;
		while ((a = in.read()) != -1) {
			out.write(a);
			out.flush();
		}
		in.close();
		out.close();
	}


}
