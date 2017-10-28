package com.data0123.fortest.test;

/**
 * @author xiaohuqi@126.com 2017-10-26 8:33
 **/
public class T0 {
	public static void main(String[] args) {
		String line = "电网调度 多媒体网络 远程通信 语音管理";
		line = "ILP(Integer Linear Programming)算法";
		line = "内容感知处理器（CAP）";
		line.replaceAll("[\\(（][^\\(（\\)）]+?[\\)）]", ""); //去除括号中内容

//		if(line.indexOf(" ") != -1){	//空格处理
//			StringBuilder sb = new StringBuilder();
//			int b = 0;
//			while((b = line.indexOf(" ", b + 1)) != -1){
//				String bs = line.substring(b - 1, b);
//				String as = line.substring(b + 1, b + 2);
//				if(bs.getBytes().length > 1 && as.getBytes().length > 1){
//					sb.append(line.substring(0, b)).append("\n");
//					line = line.substring(b + 1);
//				}
//			}
//			sb.append(line).append("\n");
//
//			System.out.println(sb.toString());
//		}




		String line0 = line.replaceAll("[\\(（][^\\(（\\)）]+?[\\)）]", "");
		System.out.println(line0);
	}
}
