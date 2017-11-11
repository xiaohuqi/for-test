package com.data0123.fortest.memory;

/**
 * @author xiaohuqi@126.com 2017-09-01 21:02
 **/
public class RuntimeConstantPoolOOM {
	public static void main(String[] args) {
		String str1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(str1.intern() == str1);

		String str2 = new StringBuilder("ja").append("v").toString();
		System.out.println(str2.intern() == str2);

		System.gc();
	}
}