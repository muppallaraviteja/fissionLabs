package com.ravi.fissionLabs.config;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ravi.fissionLabs.exception.ConcurrencyException;
import com.ravi.fissionLabs.exception.OrderNotFoundException;
import com.ravi.fissionLabs.exception.ProductNotFoundException;
import com.ravi.fissionLabs.exception.ProductQuantityInsufficientException;
import com.ravi.fissionLabs.model.ErrorDetails;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request){
    ErrorDetails errordetails =new ErrorDetails(ex.getMessage(),LocalDateTime.now(),request.getDescription(false));
    return new ResponseEntity<>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);

  }

  @ExceptionHandler(ProductNotFoundException.class)
  public final ResponseEntity<ErrorDetails> handleProductNotFoundException(Exception ex, WebRequest request){
    ErrorDetails errordetails =new ErrorDetails(ex.getMessage(),LocalDateTime.now(),request.getDescription(false));
    return new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(OrderNotFoundException.class)
  public final ResponseEntity<ErrorDetails> handleOrderNotFoundException(Exception ex, WebRequest request){
    ErrorDetails errordetails =new ErrorDetails(ex.getMessage(),LocalDateTime.now(),request.getDescription(false));
    return new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ConcurrencyException.class)
  public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(Exception ex, WebRequest request){
    ErrorDetails errordetails =new ErrorDetails(ex.getMessage(),LocalDateTime.now(),request.getDescription(false));
    return new ResponseEntity<>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ProductQuantityInsufficientException.class)
  public final ResponseEntity<ErrorDetails> handleInsufficientInventoryNotFoundException(Exception ex, WebRequest request){
    ErrorDetails errordetails =new ErrorDetails(ex.getMessage(),LocalDateTime.now(),request.getDescription(false));
    return new ResponseEntity<>(errordetails, HttpStatus.INSUFFICIENT_STORAGE);
  }



}
