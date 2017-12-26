package com.data0123.fortest.compress;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * @author xiaohuqi@126.com 2017-12-26 10:05
 **/
public class CompressDemo {
	public static void main(String[] args) {
		try{
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/temp/entname_1.dic"), "gbk"));
			BufferedReader br = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("d:/temp/entname.dic.gz")), "utf-8"));
			String line;
			while((line = br.readLine()) != null){
//				System.out.println(line);
				if(line.length() < 4){
//					System.out.println(line);
					continue;
				}
				bw.write(line.concat("\n"));
			}
			br.close();
			bw.close();

//			int count;
//			byte data[] = new byte[BUFFER];
//			while ((count = gis.read(data, 0, BUFFER)) != -1) {
//				os.write(data, 0, count);
//			}
//
//			gis.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println();
	}
}
