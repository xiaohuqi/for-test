package com.data0123.fortest.regex;

/**
 * @author xiaohuqi@126.com 2017-11-27 23:29
 **/
public class RegexT0 {
	public static void main(String[] args) {
		long t0 = System.currentTimeMillis();
		for(int i=0;i<10000000;i++){
			String str = String.valueOf(Math.random());
			if(str.matches("[\\u4e00-\\u9fa5]+")){

			}
		}
		System.out.println(System.currentTimeMillis() - t0);
	}
}
