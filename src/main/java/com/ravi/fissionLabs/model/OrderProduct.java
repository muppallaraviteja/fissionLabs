package com.ravi.fissionLabs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  public OrderProduct(Order order, Product product, int quantity) {
    this.order = order;
    this.product = product;
    this.quantity = quantity;
  }

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  private int quantity;
}
