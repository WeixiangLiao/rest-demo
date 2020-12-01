package com.mercury.beans;

import java.io.Serializable;

public class OrderInfo implements Serializable {
	private int id;
	private int qty;
	private int productId;
	
	public OrderInfo() {
		super();
	}
	public OrderInfo(int id, int qty, int productId) {
		super();
		this.id = id;
		this.qty = qty;
		this.productId = productId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		return "OrderInfo [id=" + id + ", qty=" + qty + ", productId=" + productId + "]";
	}
}
