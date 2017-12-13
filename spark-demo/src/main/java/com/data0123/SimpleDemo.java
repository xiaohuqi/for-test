package com.data0123;/**
 * Created by xiaohuqi on 2017/12/2.
 */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * @author xiaohuqi@126.com 2017/12/2
 **/
public class SimpleDemo {

	public static void main(String[] args) {
		System.setProperty("hadoop.home.dir", "Z:/dtools/hadoop-common-2.2.0-bin-master");

		String logFile = "Z:/dtools/spark-2.2.0-bin-hadoop2.7/README.md"; // Should be some file on your system
		SparkConf conf = new SparkConf().setAppName("Simple Application");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> logData = sc.textFile(logFile).cache();
		long numAs = logData.filter(new Function<String, Boolean>() {
			public Boolean call(String s) { return s.contains("a"); }
		}).count();
		long numBs = logData.filter(new Function<String, Boolean>() {
			public Boolean call(String s) { return s.contains("b"); }
		}).count();
		System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
	}
}
