package com.data0123;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaohuqi@126.com 2017-11-15 11:44
 **/
public class PiEstimationDemo {
	private static final int NUM_SAMPLES = 10;
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("spark://centos-1:7077").setAppName("pidemo");
		JavaSparkContext sc = new JavaSparkContext(conf);

		List<Integer> l = new ArrayList<>(NUM_SAMPLES);
		for (int i = 0; i < NUM_SAMPLES; i++) {
			l.add(i);
		}

		long count = sc.parallelize(l).filter(i -> {
			double x = Math.random();
			double y = Math.random();
			return x*x + y*y < 1;
		}).count();
		System.out.println("Pi is roughly " + 4.0 * count / NUM_SAMPLES);
	}
}
