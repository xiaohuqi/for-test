package com.data0123;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * @author xiaohuqi@126.com 2017-11-15 11:44
 **/
public class WordCountDemo {
	private static final int NUM_SAMPLES = 10;
	public static void main(String[] args) {
		try {
			SparkConf conf = new SparkConf().setMaster("local").setAppName("wordcount");
			JavaSparkContext sc = new JavaSparkContext(conf);

			JavaRDD<String> textFile = sc.textFile("D:\\work\\nanrui\\kmap\\corpus_all.txt");
			JavaPairRDD<String, Integer> counts = textFile
					.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
					.mapToPair(word -> new Tuple2<>(word, 1))
					.reduceByKey((a, b) -> a + b);
			counts.saveAsTextFile("d:/work/temp/out/1");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
