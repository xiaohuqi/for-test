package com.data0123.word2vector.example;

import com.data0123.word2vector.bean.WordEntry;
import com.data0123.word2vector.service.Word2VectorService;

import java.util.Set;

/**
 * @author xiaohuqi@126.com 2017/11/8
 **/
public class Word2VectorExample {
	public static void main(String[] args) {
		Set<WordEntry> set = new Word2VectorService().distance("新能源", 100, "D:/work/nanrui/kmap/corpus0.model");
		System.out.println(set);
	}
}
