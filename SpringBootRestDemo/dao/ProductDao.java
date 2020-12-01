package com.mercury.SpringBootRestDemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mercury.SpringBootRestDemo.bean.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {
	
	List<Product> getByBrand(String brand);
	List<Product> findByBrand(String brand);
	
	@Query("select p from Product p where p.price = :price")
	Product getByPrice(@Param("price") int price);
	
	List<Product> findByName(String name);
}
