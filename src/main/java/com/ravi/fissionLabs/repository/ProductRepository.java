package com.ravi.fissionLabs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.ravi.fissionLabs.model.Product;

import jakarta.persistence.LockModeType;

@Repository
public interface ProductRepository extends JpaRepository<Product,  Long> {
  @Override
  @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
  Optional<Product> findById(Long id);
}
