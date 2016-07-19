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
		fos.write("���".getBytes());
		fos.close();
	}
	
	/**
	 * ɾ�������ļ���
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
	 * ��ȡ��Ҫ���ļ�����
	 * @param destfile
	 * @param filter
	 */
	public static List<String> FindFile(File destfile,MyFileFilter filter,List<String> list){
		
		File[] files = destfile.listFiles();//ע�����ﲻӦ�ô�������
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
	 * �����ļ����ֽ�������ȡ��
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void CopyFile(File srcFile, File destFile) throws IOException {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("�ļ���" + srcFile + "�����ڣ�");
		}
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "�����ļ���");
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
	 * ��������ֽ��������ļ�
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void CopyFileByBuffer(File srcFile, File destFile)
			throws IOException {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("�ļ�" + srcFile + "�����ڣ�");
		}
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "�����ļ���");
		}
		BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(srcFile));
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(destFile));
		int c ;
		while((c = bis.read())!=-1){
			bos.write(c);
			bos.flush();//ˢ�»�����
		}
		bis.close();
		bos.close();
	}

	/**
	 * ���ֽڿ����ļ�
	 * 
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	public static void CopyFileBybyte(File srcFile, File destFile)
			throws IOException {
		if (!srcFile.exists()) {
			throw new IllegalArgumentException("�ļ���" + srcFile + "�����ڣ�");
		}
		if (!srcFile.isFile()) {
			throw new IllegalArgumentException(srcFile + "�����ļ���");
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
