package com.ravi.fissionLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.fissionLabs.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,  Long> {

}
