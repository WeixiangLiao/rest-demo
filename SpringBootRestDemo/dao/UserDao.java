package com.mercury.SpringBootRestDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mercury.SpringBootRestDemo.bean.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
}
