package com.mercury.SpringBootRestDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.SpringBootRestDemo.bean.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {

}
