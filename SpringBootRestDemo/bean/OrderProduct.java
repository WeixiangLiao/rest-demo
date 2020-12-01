package com.mercury.SpringBootRestDemo.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MSI_ORDER_PRODUCT")
public class OrderProduct {
	
	@Id
	@SequenceGenerator(name = "msi_order_product_seq_gen", sequenceName = "MSI_ORDER_product_SEQ", allocationSize = 1)
    @GeneratedValue(generator="msi_order_product_seq_gen", strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private int qty;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
	private Order order;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
	private Product product;

	public OrderProduct(int id, int qty, Order order, Product product) {
		super();
		this.id = id;
		this.qty = qty;
		this.order = order;
		this.product = product;
	}

	public OrderProduct() {
		super();
		// TODO Auto-generated constructor stub
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

	public void setQty(int qry) {
		this.qty = qry;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "OrderProduct [id=" + id + ", qty=" + qty + ", order=" + order + ", product=" + product + "]";
	}
	
	
}
