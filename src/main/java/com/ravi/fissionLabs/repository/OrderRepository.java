package com.ravi.fissionLabs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.fissionLabs.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,  Long> {

}
