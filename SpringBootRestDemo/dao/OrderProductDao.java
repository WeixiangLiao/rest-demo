package com.mercury.SpringBootRestDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.SpringBootRestDemo.bean.OrderProduct;

public interface OrderProductDao extends JpaRepository<OrderProduct, Integer> {

}
