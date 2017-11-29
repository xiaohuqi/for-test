package com.data0123;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiaohuqi@126.com 2017-11-11 9:56
 **/
public class SparkDemo0 {
	public static void main(String[] args) {
		try {
			SparkConf conf = new SparkConf().setMaster("spark://centos-1:7077").setAppName("demo0");
			JavaSparkContext sc = new JavaSparkContext(conf);

			List<Integer> data = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
			JavaRDD<Integer> distData = sc.parallelize(data);

//			JavaRDDLike javaRDDLike = ;
//			System.out.println();
			distData.reduce((a, b) -> a + b);
			sc.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
