package com.data0123.fortest.file;

import java.util.Set;

/**
 * @author xiaohuqi E-mail:xiaohuqi@126.com
 * @version 创建时间：2010-5-27 下午11:18:15
 * 
 */
public interface IReadFileUtil {
	/**
	 * @author xiaohuqi
	 *
	 * @param filePath
	 * @param charSet
	 * @return
	 */
	public String readFile2String(String filePath);
	
	public String readFileByCharSet(String filePath, String charSet);
	
	/**
	 * @author xiaohuqi
	 * @param filePath 文件路径
	 * @param charSet 文件编码
	 * @return 文件源码
	 * 按行读取文件
	 */
	public String readFileByLine(String filePath, String charSet);
	
	/**
	 * 读取行文件到Set中，每行一记录
	 * @param filePath
	 * @param charset
	 * @return
	 */
	public Set<String> readLineFile2Set(String filePath, String charset);
}
