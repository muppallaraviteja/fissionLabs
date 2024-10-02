package com.ravi.fissionLabs.service;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.ravi.fissionLabs.config.Constant;
import com.ravi.fissionLabs.exception.ConcurrencyException;
import com.ravi.fissionLabs.exception.ProductNotFoundException;
import com.ravi.fissionLabs.model.Product;
import com.ravi.fissionLabs.model.ProductDto;
import com.ravi.fissionLabs.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product getProductById(long id){
    return productRepository.findById(id)
        .orElseThrow(
            () -> new ProductNotFoundException(String.format(Constant.PRODUCT_NOT_FOUND,id)));
  }


  @Transactional
  public Product restockProduct(ProductDto dto) {
    try {
      Product product = productRepository.findById(dto.getId())
          .orElseThrow(
              () -> new ProductNotFoundException(String.format(Constant.PRODUCT_NOT_FOUND,dto.getId())));
      product.setQuantity(dto.getQuantity());
      return productRepository.save(product);
    } catch (OptimisticLockingFailureException e) {
        throw new ConcurrencyException(Constant.INTERNAL_SERVER_ERROR);
    }
  }

}
