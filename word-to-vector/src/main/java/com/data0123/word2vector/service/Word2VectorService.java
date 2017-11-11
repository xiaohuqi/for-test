package com.data0123.word2vector.service;

import com.data0123.word2vector.algorithm.Word2VEC;
import com.data0123.word2vector.bean.WordEntry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Set;

/**
 * @author xiaohuqi@126.com 2017/11/8
 **/
public class Word2VectorService {
	private static final Log log = LogFactory.getLog(Word2VectorService.class);


	/**
	 *  计算语义距离
	 * @param word 查询目标词列表
	 * @param size 输出数量，会按语义距离降序排列
	 * @return 词、距离列表
	 */
	public Set<WordEntry> distance(String word, int size, String modelFilePath){
		try {
			Word2VEC w2v = new Word2VEC();
			w2v.loadJavaModel(modelFilePath);
			return w2v.distance(word, size);
		}catch(Exception e){
			log.error("distance failed...", e);
		}
		return null;
	}

	/**
	 *  计算与词列表的语义距离
	 * @param words 查询目标词列表
	 * @param size 输出数量，会按语义距离降序排列
	 * @return 词、距离列表
	 */
	public Set<WordEntry> distance(List<String> words, int size, String modelFilePath){
		try {
			Word2VEC w2v = new Word2VEC();
			w2v.loadJavaModel(modelFilePath);
			return w2v.distance(words, 1000);
		}catch(Exception e){
			log.error("distance failed...", e);
		}
		return null;
	}
}
