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
			SparkConf conf = new SparkConf().setMaster("spark://centos-1:7077").setAppName("wordcount");
			JavaSparkContext sc = new JavaSparkContext(conf);

			JavaRDD<String> textFile = sc.textFile("D:\\Java\\workspace\\for-test\\src\\main\\resources\\log4j.properties");
			JavaPairRDD<String, Integer> counts = textFile
					.flatMap(s -> Arrays.asList(s.split(" ")).iterator())
					.mapToPair(word -> new Tuple2<>(word, 1))
					.reduceByKey((a, b) -> a + b);
			counts.saveAsTextFile("d:/work/temp/out");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
