package com.dev.challenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.challenge.model.Category;
import com.dev.challenge.model.Product;
import com.dev.challenge.repository.CategoryRepository;
import com.dev.challenge.repository.ProductRepository;

@RestController
@RequestMapping(path="/shop")
@CrossOrigin
public class ShopController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	
	//================== Category endpoints ======================

	@GetMapping("/category")
	List<Category> all() {
		return (List<Category>) categoryRepository.findAll();
	}
	
	@PostMapping(path="/category/add")
	public Category addCategory(@RequestBody Category cat) {
		return categoryRepository.save(cat);
	}
	
	@GetMapping("/category/{id}")
	Optional<Category> getCategory(@PathVariable int id) {
	    return categoryRepository.findById(id);
	}
	
	@PutMapping("/category/{id}")
	Category updateCategory(@RequestBody Category cat, @PathVariable int id) {
	    
	    return categoryRepository.findById(id)
	      .map(category -> {
	    	  category.setName(cat.getName());
	        return categoryRepository.save(category);
	      })
	      .orElseGet(() -> {
	    	  cat.setId(id);
	        return categoryRepository.save(cat);
	      });
	}
	
	@DeleteMapping("/category/{id}")
	void deleteCategory(@PathVariable int id) {
		categoryRepository.deleteById(id);
	}
	
	//================== Product endpoints ======================
	
	@GetMapping("/product")
	List<Product> allProducts() {
		return (List<Product>) productRepository.findAll();
	}
	
	@PostMapping(path="/product/add")
	public Product addProduct(@RequestBody Product prod) {
		return productRepository.save(prod);
	}
	
	@GetMapping("/product/{id}")
	Optional<Product> getProduct(@PathVariable int id) {
	    return productRepository.findById(id);
	}
	
	@PutMapping("/product/{id}")
	Product updateProduct(@RequestBody Product prod, @PathVariable int id) {
	    
	    return productRepository.findById(id)
	      .map(product -> {
	    	  product.setName(prod.getName());
	    	  product.setPrice(prod.getPrice());
	    	  product.setCategory(prod.getCategory());
	        return productRepository.save(product);
	      })
	      .orElseGet(() -> {
	    	  prod.setId(id);
	        return productRepository.save(prod);
	      });
	}
	
	@DeleteMapping("/product/{id}")
	void deleteProduct(@PathVariable int id) {
		productRepository.deleteById(id);
	}

}
