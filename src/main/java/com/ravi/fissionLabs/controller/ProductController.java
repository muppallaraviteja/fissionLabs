package com.ravi.fissionLabs.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ravi.fissionLabs.model.Product;
import com.ravi.fissionLabs.model.ProductDto;
import com.ravi.fissionLabs.repository.ProductRepository;
import com.ravi.fissionLabs.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable long id){
    return productService.getProductById(id);
  }

  @PutMapping("/update")
  public Product update(@RequestBody ProductDto dto) {
    return productService.restockProduct(dto);

  }

}
