package jp.gr.java_conf.saka.plantuml.viewer.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(ImmutableMap.of("error", e.getMessage()));
  }
}
