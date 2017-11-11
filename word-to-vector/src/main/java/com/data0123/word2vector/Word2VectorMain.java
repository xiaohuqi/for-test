package com.data0123.word2vector;

import com.data0123.word2vector.algorithm.Word2VEC;

import java.util.Arrays;

/**
 * @author xiaohuqi@126.com 2017-10-28 10:20
 **/
public class Word2VectorMain {
	public static void main(String[] args) {
		try{
			//进行训练
//			Learn lean = new Learn() ;
//			lean.learnFile(new File("D:\\work\\nanrui\\kmap\\corpus0.txt")) ;
//			lean.saveModel(new File("D:\\work\\nanrui\\kmap\\corpus0.model")) ;

			//加载测试
			Word2VEC w2v = new Word2VEC() ;
			w2v.loadJavaModel("D:\\work\\nanrui\\kmap\\corpus0.model") ;

			System.out.println(w2v.distance("新能源", 1000));
			System.out.println(w2v.distance(Arrays.asList("电动汽车", "直流输电"), 10));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
