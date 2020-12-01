package com.mercury.SpringBootRestDemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.SpringBootRestDemo.bean.Order;
import com.mercury.SpringBootRestDemo.http.Response;
import com.mercury.SpringBootRestDemo.jms.OrderInfoProducer;
import com.mercury.SpringBootRestDemo.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderInfoProducer oip;
	
	@GetMapping
	public List<Order> getAll() {
		return orderService.getAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Order getById(@PathVariable int id) {
		return orderService.getOrderById(id);
	}
	
	@PostMapping
	public Response save(@RequestBody Order order) {
		return orderService.save(order);
	}
	
	@GetMapping("/report")
	@PreAuthorize("permitAll()")
	public void report() {
		oip.sendOrderForReport(12);
	}
}
