package com.baidu.tq;

import java.util.List;

/**
 * 封装分页属性
 * @author zhaoqx
 *
 */
public class PageBean {
	private int total;//总记录数
	private List rows;//当前页需要展示的数据集合
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
}
