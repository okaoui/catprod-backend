package com.dev.challenge.repository;

import org.springframework.data.repository.CrudRepository;

import com.dev.challenge.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
}
