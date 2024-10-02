package com.ravi.fissionLabs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ravi.fissionLabs.config.Constant;
import com.ravi.fissionLabs.exception.OrderNotFoundException;
import com.ravi.fissionLabs.exception.ProductDtoException;
import com.ravi.fissionLabs.model.Order;
import com.ravi.fissionLabs.model.OrderProduct;
import com.ravi.fissionLabs.model.Product;
import com.ravi.fissionLabs.model.ProductDto;
import com.ravi.fissionLabs.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

  private final OrderRepository orderRepository;
  private final ProductService productService;

  public OrderService(OrderRepository orderRepository, ProductService productService) {
    this.orderRepository = orderRepository;
    this.productService = productService;
  }

  @Transactional
  public Order createOrder(List<ProductDto> dtos) {
    validateOrderSize(dtos);

    List<OrderProduct> orderProducts = dtos.stream()
        .map(this::createOrderProduct)
        .collect(Collectors.toList());

    Order order = new Order();
    order.setOrderProducts(orderProducts);
    orderProducts.forEach(orderProduct -> orderProduct.setOrder(order));
    return orderRepository.save(order);
  }

  private OrderProduct createOrderProduct(ProductDto dto) {
    Product existingProduct = productService.getProductById(dto.getId());
    if (existingProduct.getQuantity() < dto.getQuantity()) {
      throw new ProductDtoException(String.format(Constant.PRODUCT_INVENTORY_INSUFFICIENT, dto.getId()));
    }
    OrderProduct orderProduct = new OrderProduct();
    orderProduct.setProduct(existingProduct);
    orderProduct.setQuantity(dto.getQuantity());
    dto.setQuantity(existingProduct.getQuantity() - dto.getQuantity());
    productService.restockProduct(dto);
    return orderProduct;
  }



  public void cancelOrder(Long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderNotFoundException(String.format(Constant.ORDER_NOT_FOUND, orderId)));
    order.getOrderProducts().forEach(this::restockProduct);
    orderRepository.delete(order);
  }

  private void restockProduct(OrderProduct orderProduct) {
    Product existingProduct = productService.getProductById(orderProduct.getProduct().getId());
    existingProduct.setQuantity(existingProduct.getQuantity() + orderProduct.getQuantity());
    productService.restockProduct(new ProductDto(existingProduct.getId(), existingProduct.getQuantity()));
  }

  public Order getOrder(Long orderId) {
    return orderRepository.findById(orderId)
        .orElseThrow(
            () -> new OrderNotFoundException(String.format(Constant.ORDER_NOT_FOUND, orderId)));
  }

  private void validateOrderSize(List<ProductDto> dtos) {
    if (dtos.size() > 3) {
      throw new ProductDtoException("An order can contain only up to 3 products.");
    }
  }


}
