package com.data0123.fortest.word2vector;

import java.io.File;

/**
 * @author xiaohuqi@126.com 2017-10-28 10:20
 **/
public class Word2VectorMain {
	public static void main(String[] args) {
		try{
			//进行分词训练

			Learn lean = new Learn() ;

			lean.learnFile(new File("corpus/result.txt")) ;

			lean.saveModel(new File("model/vector.mod")) ;



			//加载测试

			Word2VEC w2v = new Word2VEC() ;

			w2v.loadJavaModel("model/vector.mod") ;

			System.out.println(w2v.distance("姚明")); ;
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
