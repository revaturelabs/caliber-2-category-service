package com.revaturelabs.caliber.category.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CategoryConflictException extends RuntimeException {
  public CategoryConflictException(String msg) {
    super(msg);
  }
}
