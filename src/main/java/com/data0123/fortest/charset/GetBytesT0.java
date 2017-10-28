package com.data0123.fortest.charset;

/**
 * @author xiaohuqi@126.com 2017-10-27 9:40
 **/
public class GetBytesT0 {
	public static void main(String[] args) {
		try {
			System.out.println("0".getBytes("utf-8")[0]);
			System.out.println();
			System.out.println("$".matches("[\\x00-\\xff]+"));
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
