package com.ravi.fissionLabs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INSUFFICIENT_STORAGE)
public class ProductQuantityInsufficientException extends RuntimeException {

  public ProductQuantityInsufficientException(String message) {
    super(message);
  }
}
