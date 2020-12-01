package com.mercury.SpringBootRestDemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.SpringBootRestDemo.bean.Sample;

@RestController
@RequestMapping("/samples")
public class SampleController {
	
	@Value("${tp}")
	private String testProperty;
	
	@GetMapping("/{name}")
	public Sample get(@PathVariable String name) {
		System.out.println(testProperty);
		return new Sample(name,10);
	}
	
	
	@GetMapping
	public List<Sample> getAll(){
		return new ArrayList();
	}
}
