package com.data0123.fortest.memory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * @author xiaohuqi@126.com 2017/12/28
 **/
public class ListAndArrayMemoryT0 {
	public static void main(String[] args) {
		try{
			long t0 = System.currentTimeMillis();
			List<String> list = new ArrayList<>();
//			String[] array = new String[37776560];
			BufferedReader bReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("D:/work/ie/model/ner/entname.dic.gz")), "gbk"));
			String aLine;
//			int c = 0;
			while((aLine = bReader.readLine()) != null){
				list.add(aLine.trim());
//				array[c ++] = aLine.trim();
			}

			bReader.close();
//			System.out.println("------list: " + list.size());
			System.out.println(list.get(0));
			System.out.println("\t..." + (System.currentTimeMillis() - t0));
//
//			list.toArray(array);
//			System.out.println(array[0]);
			Collections.sort(list);
			System.out.println(list.get(0));
			System.out.println("\t..." + (System.currentTimeMillis() - t0));

			Thread.sleep(1000000L);

//			Collections.sort(list);
//			System.out.println(list.get(0));
			System.out.println("\t..." + (System.currentTimeMillis() - t0));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
