package com.data0123.fortest.file;
/**
 * @author xiaohuqi E-mail:xiaohuqi@126.com
 * @version 创建时间：2010-5-28 下午04:23:18
 * 
 */
public interface IWriteFileUtil {
	public boolean writeFileByCharSet(String fileSource, String filePath, String charSet);
	/**
	 * @param fileSource 新内容
	 * @param filePath	文件路径
	 * @param charset	文件编码
	 * @return 成功返回true，发生异常返回false。
	 * 把新的内容添加到文件的末尾
	 */
	public boolean appendFile(String fileSource, String filePath, String charset);
	
	/**
	 * 复制文件
	 * @param srcPath	源文件路径
	 * @param dstPath	目标文件路径
	 */
	public void copyFile(String srcPath, String dstPath);
}
