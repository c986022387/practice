package practice;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FileCopyTest {

	@Test
	public void testmyTry(){
		try {
			IOandFile.myTry(new File("C:/Users/amd/Desktop/file3.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testdelete() {
		IOandFile.delete(new File("C:/Users/amd/Desktop/file3.txt"));
	}

	@Test
	public void testFindFile() {
		List<String> list;
		list = IOandFile.FindFile(new File("C:/Users/amd/Desktop/ImoocjavaÔ´Âë"),
				new MyFileFilter(".java"), new ArrayList<String>());
		System.out.println(list.size());
		for (String s : list) {
			System.out.println(s);
		}
	}

	@Test
	public void testCopyFile() {
		try {
			long start = System.currentTimeMillis();
			IOandFile.CopyFile(new File("C:/Users/amd/Desktop/file.txt"),
					new File("C:\\Users\\amd\\Desktop\\file2.txt"));
			long end = System.currentTimeMillis();
			System.out.println(end - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCopyFileByBuffer() {
		try {
			long start = System.currentTimeMillis();
			IOandFile.CopyFileByBuffer(
					new File("C:/Users/amd/Desktop/°¢Å£.flac"), new File(
							"C:\\Users\\amd\\Desktop\\°¢Å£1.flac"));
			long end = System.currentTimeMillis();
			System.out.println(end - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCopyFileBybyte() {
		long start = System.currentTimeMillis();
		try {
			IOandFile.CopyFileBybyte(new File("C:/Users/amd/Desktop/file.txt"),
					new File("C:\\Users\\amd\\Desktop\\file3.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

	}

}
