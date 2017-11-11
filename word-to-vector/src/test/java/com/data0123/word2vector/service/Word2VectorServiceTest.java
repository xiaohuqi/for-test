package com.data0123.word2vector.service;

import com.data0123.word2vector.bean.WordEntry;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by xiaohuqi on 2017/11/8.
 */
public class Word2VectorServiceTest extends TestCase {
	public void testDistance() throws Exception {
		Set<WordEntry> set = new Word2VectorService().distance("新能源", 100, "D:/work/nanrui/kmap/corpus0.model");
		System.out.println(set);
	}

	public void testDistance1() throws Exception {
		Set<WordEntry> set = new Word2VectorService().distance(Arrays.asList("新能源", "电动汽车"), 100, "D:/work/nanrui/kmap/corpus0.model");
		System.out.println(set);
	}

}