package com.mercury.SpringBootRestDemo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mercury.SpringBootRestDemo.bean.Product;
import com.mercury.SpringBootRestDemo.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAll() {
		return productService.getAll();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("permitAll()")
	public Product getProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}
	
	@GetMapping("/brand/{brand}")
	public List<Product> getProductByBrand(@PathVariable String brand) {
		
		log.debug("debug level");
		log.info("info level");
		return productService.getByBrand(brand);
	}
	
	@PostMapping
	public void save(@RequestBody Product product) {
		System.out.println("Saving: " + product);
		productService.save(product);
	}
	
	@GetMapping("/pdf")
	public ResponseEntity<InputStreamResource> getProductsInPDF() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=products.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(productService.getProductsInPDF()));
	}
	
	@Cacheable("res")
	@GetMapping("/add/{x}/{y}")
	public int add(@PathVariable int x,@PathVariable int y) {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return x+y;
	}
	
	@GetMapping("/mul/{x}/{y}")
	public int mul(@PathVariable int x,@PathVariable int y) {
		return x*y;
	}
}