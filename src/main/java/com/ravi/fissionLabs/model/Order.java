package com.ravi.fissionLabs.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderProduct> orderProducts = new ArrayList<>();
}

