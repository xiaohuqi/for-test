package com.data0123.fortest.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ReadFileUtil implements IReadFileUtil {
	private static final Log log = LogFactory.getLog(ReadFileUtil.class);
	
	public static byte[] readFile2Byte(String filePath){
		byte[] bBuf;
		try{
			File file = new File(filePath);
			if(!file.exists()){	//文件不存在则返回空值
				log.error("In readFile...文件不存在!");	
				return null;
			}
			FileInputStream fis = new FileInputStream(file);

			byte tempBuf[] = new byte[(int)(file.length())];
			fis.read(tempBuf);
			int noOfSpace = 0;
			for(int i=tempBuf.length-1;i>=0;i--){
				if(tempBuf[i] == 0){  
					++ noOfSpace;
				}
				else{
					break;
				}
			}
			
			bBuf = new byte[(int)file.length()-noOfSpace];
			for(int i=0;i<bBuf.length;i++){
				bBuf[i] = tempBuf[i];
			}
			
			fis.close();
			
		}catch(Exception e){
			log.error("In readFile..读文件时发生错误！！！错误原因为：\n" + e);	
			return null;
		}
		return bBuf;
	}
	
	/**
	 * @author xiaohuqi
	 *
	 * @param filePath
	 * @param charSet
	 * @return
	 */
	public String readFile2String(String filePath){
		String fileSrc = "";
		try{
			File file = new File(filePath);
			if(!file.exists()){	//文件不存在则返回空值
				log.error("In readFile2String...文件不存在!");	
				return "";
			}
			FileInputStream fis = new FileInputStream(file);

			byte[] cbuf = new byte[(int)(file.length())];
			fis.read(cbuf);
			int noOfSpace = 0;
			for(int i=cbuf.length-1;i>=0;i--){
				if(cbuf[i] == 0){
					++ noOfSpace;
				}
				else{
					break;
				}					
			}
			fileSrc =new String(cbuf);
			fileSrc = fileSrc.substring(0, fileSrc.length() - noOfSpace);
			
			fis.close();
		}catch(Exception e){
			log.error("In readFile2String..读文件时发生错误！！！错误原因为：\n" + e);	
			return "";
		}
		return fileSrc;
	}
	
	public String readFileByCharSet(String filePath, String charSet){
		try{
			File file = new File(filePath);
			if(!file.exists()){	//文件不存在则返回空值
				log.error("In readFile2String...文件不存在!");	
				return "";
			}
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charSet));
			StringBuffer buf = new StringBuffer();
			char[] cbuf = new char[102400];
			
			int len = -1;
			while((len = bReader.read(cbuf)) != -1){
				buf.append(cbuf, 0, len);
			}
			
			bReader.close();
			file = null;
			cbuf = null;
			
			return buf.toString();
		}catch(Exception e){
			log.error("In readFileByCharSet...读文件时发生错误！！！错误原因为：\n" + e);	
			return "";
		}
	}
	
	/**
	 * @author xiaohuqi
	 * @param filePath 文件路径
	 * @param charSet 文件编码
	 * @return 文件源码
	 * 按行读取文件
	 */
	public String readFileByLine(String filePath, String charSet){
//		log.info("开始读取文件：" + filePath);
		try{
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charSet));
			
			String aLine = "";
			int i = 1;
			while((aLine = bReader.readLine()) != null){	
//				new String(aLine.getBytes(), "utf-8").trim();
//				System.out.println(new String(aLine.getBytes(), "utf-8").trim());
				System.out.println((i ++) + "\t" + aLine);
			}
			bReader.close();
		}catch(FileNotFoundException fnfe){
			log.error("文件" + filePath + "不存在：" + fnfe);
		}catch(IOException ioe){
			log.error("读取文件" + filePath + "时发生错误：" + ioe);
		}

		return "";
	}
	
	/**
	 * @author xiaohuqi
	 * @param filePath 文件路径
	 * @param charSet 文件编码
	 * @return 文件源码
	 * 按行读取文件
	 */
	public String randomReadFileByLine(String filePath, String charSet){
//		log.info("开始读取文件：" + filePath);
		try{
			RandomAccessFile raFile = new RandomAccessFile(filePath, "r");
//			raFile.readLine();
//			System.out.println(raFile.length());
			raFile.seek(raFile.length()/2);
			raFile.readLine();
			String aLine = new String(raFile.readLine().getBytes("iso-8859-1"), "utf-8");
//			while((aLine = raFile.readLine()) != null){				
//				System.out.println(new String(aLine.getBytes(), "utf-8").trim());
//			}
			System.out.println(aLine.trim());
			raFile.close();
		}catch(FileNotFoundException fnfe){
			log.error("文件" + filePath + "不存在：" + fnfe);
		}catch(IOException ioe){
			log.error("读取文件" + filePath + "时发生错误：" + ioe);
		}

		return "";
	}
	
	/**
	 * 读取行文件到Set中，每行一记录
	 * @param filePath
	 * @param charset
	 * @return
	 */
	public Set<String> readLineFile2Set(String filePath, String charset){
		Set<String> set = new HashSet<String>();
		try{
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charset));
			
			String aLine = "";
			while((aLine = bReader.readLine()) != null){	
				set.add(aLine);
			}
			bReader.close();
		}catch(Exception e){
			log.error("In readFile2Set, read file ".concat(filePath).concat(" error! ") + e);
		}
		return set;
	}

}
