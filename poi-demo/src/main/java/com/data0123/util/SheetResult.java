package com.data0123.util;

import java.util.List;
import java.util.Map;

/**
 * Sheet页处理封装结果
 * @author 游盼盼
 * @since Excel Study 1.0
 * @author xiaohuqi@126.com 2017-11-21 10:49
 */
public class SheetResult {

	/**
	 * 记录列名与数据索引
	 */
	private Map<String, Integer> colNameMap;

	/**
	 * 头部信息的行数
	 */
	private int headRowNum;

	/**
	 * 数据集合
	 */
	private List<List<String>> dataList;

	public Map<String, Integer> getColNameMap() {
		return colNameMap;
	}

	public void setColNameMap(Map<String, Integer> colNameMap) {
		this.colNameMap = colNameMap;
	}


	public int getHeadRowNum() {
		return headRowNum;
	}

	public void setHeadRowNum(int headRowNum) {
		this.headRowNum = headRowNum;
	}

	public List<List<String>> getDataList() {
		return dataList;
	}

	public void setDataList(List<List<String>> dataList) {
		this.dataList = dataList;
	}


}
