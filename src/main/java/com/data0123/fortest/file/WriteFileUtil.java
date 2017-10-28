package com.data0123.fortest.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

/**
 * @author xiaohuqi E-mail:xiaohuqi@126.com
 * @version 创建时间：2010-5-19 下午09:24:59
 * 说明
 */

public class WriteFileUtil implements IWriteFileUtil {
	byte[] buf = new byte[4096];
	
	public boolean writeALine(String filePath){
		try{
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(filePath));
			bWriter.newLine();
			bWriter.write("1234");
			bWriter.newLine();
			bWriter.write("5678");
			bWriter.flush();
			bWriter.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		return true;
	}
	
	public boolean writeFileByCharSet(String fileSource, String filePath, String charSet){
		File targetFile = new File(filePath);
		try{
//			if(targetFile.exists()){
//				targetFile.delete();
//			}
//			targetFile.createNewFile();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), charSet));
			
			bw.write(fileSource);
			bw.close();
		}catch(Exception e){
			String targetDir = filePath.substring(0,filePath.lastIndexOf(File.separator));
			new File(targetDir).mkdirs();
			try{
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), charSet));
				
				bw.write(fileSource);
				bw.close();
			}catch(Exception e1){
				System.out.println(e1);
			}
			System.out.println("目录文件不存在，重建");
			return false;
		}
		return true;
	}
	
	/**
	 * @param fileSource 新内容
	 * @param filePath	文件路径
	 * @param charset	文件编码
	 * @return 成功返回true，发生异常返回false。
	 * 把新的内容添加到文件的末尾
	 */
	public boolean appendFile(String fileSource, String filePath, String charset){
		try{
			RandomAccessFile file = new RandomAccessFile(filePath, "rw");
			file.seek(file.length());
			file.write(fileSource.getBytes(charset));
			file.close();
		}catch(Exception e){
			String targetDir = filePath.substring(0,filePath.lastIndexOf("/"));
			new File(targetDir).mkdirs();
			try{
				RandomAccessFile file = new RandomAccessFile(filePath, "rw");
				file.seek(file.length());
				file.write(fileSource.getBytes(charset));
				file.close();
			}catch(Exception e1){
				System.out.println(e1);
			}
			System.out.println("目录文件不存在，重建");
			return false;
		}
		return true;
	}
	
	/**
	 * @author xiaohuqi
	 * @param sourceBytes
	 * @return
	 * 去除位于byte数组后面的0字符
	 */
	public static byte[] trimByteArray(byte[] sourceBytes){
		int cutPos = sourceBytes.length - 1;
		for(int i=sourceBytes.length-1;i>=0;i--){
			if(sourceBytes[i] != 0){
				cutPos = i;
				break;
			}
		}
		byte[] returnBytes = new byte[cutPos+1];
		for(int i=0;i<=cutPos;i++){
			returnBytes[i] = sourceBytes[i];
		}
		return returnBytes;
	}
	
	/**
	 * 复制文件
	 * @param srcPath	源文件路径
	 * @param dstPath	目标文件路径
	 */
	public void copyFile(String srcPath, String dstPath){
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcPath));
			
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dstPath));
			int c;
			while ((c = bis.read(buf)) > 0) {
				bos.write(buf, 0, c);
			}
			bis.close();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
