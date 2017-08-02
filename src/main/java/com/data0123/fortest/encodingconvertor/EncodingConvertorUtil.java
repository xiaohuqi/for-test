package com.data0123.fortest.encodingconvertor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * @author xiaohuqi E-mail:xiaohuqi@126.com
 * @version 创建时间：2010-3-17 下午03:22:21
 * 说明
 */

public class EncodingConvertorUtil {
	private static final Log log = LogFactory.getLog(EncodingConvertorUtil.class);
	
	private static String baseDir = "";
	
	public static boolean copyFile(String fileFrom, String fileTo) {
		log.debug("In copyFile, begin......");
		try{
			FileInputStream in = new java.io.FileInputStream(fileFrom);
			String targetFilePath = fileTo + fileFrom.substring(baseDir.length());
            File targetDirFile = new File(targetFilePath.substring(0, targetFilePath.lastIndexOf("\\")));
			if(!targetDirFile.exists()){
				targetDirFile.mkdirs();
			}
            FileOutputStream out = new FileOutputStream(targetFilePath);
            byte[] bt = new byte[1024];
            int count;
            while ((count = in.read(bt)) > 0) {
                out.write(bt, 0, count);
            }
            in.close();
            out.close();
			log.debug("In copyFile, end.");
		}catch(Exception e){
			log.error("In copyFile...复制文件时发生错误！！！错误原因为：\n" + e);
			return false;
		}
        return true;
    }
	
	public static String readFile(String filePath, String charSet){
		log.debug("In readFile, begin......");
		String fileSrc = "";
		try{
			File file = new File(filePath);
			if(!file.exists()){	//文件不存在则返回空值
				log.error("In readFile...文件不存在!");
				return "";
			}
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, charSet);

			char[] cbuf = new char[(int)(file.length())];
			isr.read(cbuf);
			int noOfSpace = 0;
			for(int i=cbuf.length-1;i>=0;i--){
				if(cbuf[i] == 0){
					++ noOfSpace;
				}
			}
			fileSrc = String.valueOf(cbuf);
			fileSrc = fileSrc.substring(0, fileSrc.length() - noOfSpace);
			
			isr.close();
			fis.close();
			
			log.debug("In readFile, end.");
		}catch(Exception e){
			log.error("In readFile...读文件时发生错误！！！错误原因为：\n" + e);
			return "";
		}
		return fileSrc;
	}
	
	public static boolean writeFile(String filePath, String fileSrc, String charSet){
		log.debug("In writeFile, begin......");
		try{
			File file = new File(filePath);
			if(file.exists()){	//文件存在则删除
				file.delete();		
			}
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, charSet);
			osw.write(fileSrc);
			osw.flush();
			osw.close();			
			fos.close();
			//infile.writeUTF(fileSrc);
			//dos.writeUTF(fileSrc);
			//dos.close();
			//infile.close();
			log.debug("In writeFile, end.");
		}catch(Exception e){
			log.error("In writeFile...写文件时发生错误！！！错误原因为：\n" + e);
			return false;
		}
		return true;
	}
	
	/**   
	 *    
	 * @param sourceFilePath    源文件路径
	 * @param targetFilePath   输出文件路径
	 * @param sourceCharSet  源文件编码
	 * @param targetCharSet    目标文件编码
	 */   
	public static boolean convertFileEncoding(String sourceFilePath, String targetFilePath, 
			String sourceCharSet, String targetCharSet){
		log.debug("In convertFileEncoding, begin......");
		try{
			InputStream in = new FileInputStream(sourceFilePath);
			targetFilePath = targetFilePath + sourceFilePath.substring(baseDir.length());
			File targetDirFile = new File(targetFilePath.substring(0, targetFilePath.lastIndexOf("\\")));
			if(!targetDirFile.exists()){
				targetDirFile.mkdirs();
			}
			OutputStream out = new FileOutputStream(targetFilePath);
			
			Reader r = new BufferedReader(new InputStreamReader(in, sourceCharSet.trim()));    
			Writer w = new BufferedWriter(new OutputStreamWriter(out, targetCharSet.trim()));    
	
			char[] buffer = new char[4096];    
			int len;    
			while ((len = r.read(buffer)) != -1){  
				w.write(buffer, 0, len);
			}
			r.close();    
			w.flush();    
			w.close();
		}catch(Exception e){
			log.error("In convertFileEncoding...转换文件编码时发生错误！！！错误原因为：\n" + e);
		}
		return true;
	}

	/**
	 * @deprecated
	 * @author xiaohuqi
	 * @param sourceFilePath
	 * @param targetFilePath
	 * @param sourceCharSet
	 * @param targetCharSet
	 * @return
	 *
	 */
	public static boolean _convertFileEncoding(String sourceFilePath, String targetFilePath, 
			String sourceCharSet, String targetCharSet){
		log.debug("In convertFileEncoding, begin......");
		try{
			File file = new File(sourceFilePath);
			if(!file.exists()){	//文件不存在则返回false
				return false;
			}			
			String fileSrc = readFile(sourceFilePath, sourceCharSet.trim());
			targetFilePath = targetFilePath + sourceFilePath.substring(baseDir.length());
			File targetDirFile = new File(targetFilePath.substring(0, targetFilePath.lastIndexOf("\\")));
			if(!targetDirFile.exists()){
				targetDirFile.mkdirs();
			}
			writeFile(targetFilePath, fileSrc, targetCharSet.trim());
			
			log.debug("In convertFileEncoding, end.");
		}catch(Exception e){
			log.error("In convertFileEncoding...转换文件编码时发生错误！！！错误原因为：\n" + e);
			return false;
		}
		return true;
	}
		
	public static boolean convertDirEncoding(String dirPath, String targetDirPath, 
			String sourceCharSet, String targetCharSet, String suffix){
		log.debug("In convertDirEncoding, begin......");
		try{
			File file = new File(dirPath);
			if(!file.exists()){	//目录不存在则返回false
				return false;
			}
			File[] fileArray = file.listFiles();
			for(int i=0;i<fileArray.length;i++){
				File tempFile = fileArray[i];
				String tempFileName = tempFile.getName();
				if(tempFile.isFile()){
					try{
						String tempSourceFilePath = tempFile.getAbsolutePath();
						
						//判断后缀名是否符合
						if(suffix.equals("") || (tempFileName.lastIndexOf(".") != -1 && suffix.indexOf(tempFileName.substring(tempFileName.lastIndexOf("."))) >= 0)){
							convertFileEncoding(tempSourceFilePath, targetDirPath, sourceCharSet, targetCharSet);
						}
						else{
							log.error("In convertDirEncoding...文件" + tempSourceFilePath + "后缀名不符！");
							copyFile(tempSourceFilePath, targetDirPath);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				else if(tempFile.isDirectory()){
					convertDirEncoding(tempFile.getAbsolutePath(), targetDirPath, sourceCharSet, targetCharSet, suffix);
				}
			}
			log.debug("In convertDirEncoding, end.");
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error("In convertEncoding...转换编码时发生错误！！！错误原因为：\n" + sw.toString());
			return false;
		}
		return true;
	}
	
	public static boolean convertEncoding(String filePath, String targetDirPath, 
			String sourceCharSet, String targetCharSet, String suffix){
		log.debug("In convertEncoding, begin......");
		try{
			File file = new File(filePath);
			if(!file.exists()){	//文件不存在则返回false
				throw new Exception("源文件 不存在！");
			}
			File targetDirFile = new File(targetDirPath);
			if(!targetDirFile.exists()){
				targetDirFile.mkdirs();
				log.error("In convertEncoding...目录路径不存在，自动创建。\n");
			}
			if(file.isDirectory()){
				baseDir = filePath;
				baseDir = baseDir.trim();
				if(baseDir.charAt(baseDir.length()-1) == '\\'){
					baseDir = baseDir.substring(0, baseDir.length()-1);
				}
				baseDir = baseDir.substring(0, baseDir.lastIndexOf("\\"));
				convertDirEncoding(filePath, targetDirPath, sourceCharSet, targetCharSet, suffix);				
			}
			else if(file.isFile()){
				String fileName = file.getName();
				
				//判断后缀名是否符合
				if(suffix.equals("") || suffix.indexOf(fileName.substring(fileName.lastIndexOf("."))) >= 0){	
					convertFileEncoding(filePath, targetDirPath, sourceCharSet, targetCharSet);
				}
				else{
					log.info("In convertEncoding...文件" + filePath + "后缀名不符！");
					copyFile(filePath, targetDirPath);
					return false;					
				}
			}
			log.debug("In convertEncoding, end.");
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error("In convertEncoding...转换编码时发生错误！！！错误原因为：\n" + sw.toString());
			return false;
		}
		return true;
	}
}
