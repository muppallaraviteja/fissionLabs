package com.ravi.fissionLabs.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.ravi.fissionLabs.model.ProductDto;
import com.ravi.fissionLabs.service.OrderService;
import com.ravi.fissionLabs.model.Order;

@RestController
@RequestMapping("/order")
public class OrderController {

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }


  @DeleteMapping("/{id}")
  public void deleteOrder(@PathVariable long id) {
    orderService.cancelOrder(id);
  }

  @GetMapping("/{id}")
  public void getOrder(@PathVariable long id) {
    orderService.cancelOrder(id);
  }

  @PutMapping("/create")
  public ResponseEntity<Order> createOrder(@RequestBody List<ProductDto> dtos) {
    Order order = orderService.createOrder(dtos);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
        .buildAndExpand(order.getId()).toUri();
    return ResponseEntity.created(uri).build();

  }

}
