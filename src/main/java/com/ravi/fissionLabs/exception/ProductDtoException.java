package com.ravi.fissionLabs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
public class ProductDtoException extends RuntimeException {

  public ProductDtoException(String message) {
    super(message);
  }
}
