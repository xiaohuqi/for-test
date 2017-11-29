package com.data0123.word2vector;

import com.data0123.word2vector.algorithm.Learn;

import java.io.File;

/**
 * @author xiaohuqi@126.com 2017-10-28 10:20
 **/
public class Word2VectorMain {
	public static void main(String[] args) {
		try{
			//进行训练
			Learn lean = new Learn() ;
			lean.learnFile(new File("D:\\work\\nanrui\\kmap\\corpus_all.txt")) ;
			lean.saveModel(new File("D:\\work\\nanrui\\kmap\\corpus0.model")) ;

			//加载测试
//			Word2VEC w2v = new Word2VEC() ;
//			w2v.loadJavaModel("D:/work/nanrui/kmap/nanrui.model") ;
//
//			System.out.println(w2v.distance("电报方程", 100));
//			System.out.println(w2v.distance(Arrays.asList("电动汽车", "直流输电"), 10));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
