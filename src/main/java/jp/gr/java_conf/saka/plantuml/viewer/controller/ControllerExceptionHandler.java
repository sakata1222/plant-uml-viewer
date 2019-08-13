package jp.gr.java_conf.saka.plantuml.viewer.controller;

import com.google.common.collect.ImmutableMap;
import java.lang.invoke.MethodHandles;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

  private static final Logger LOGGER = LoggerFactory
    .getLogger(MethodHandles.lookup().lookupClass());

  private static Map<Class<?>, HttpStatus> EXCEPTION_TO_STATUS = ImmutableMap.<Class<?>, HttpStatus>builder()
    .put(IllegalArgumentException.class, HttpStatus.BAD_REQUEST)
    .build();

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleIllegalArgumentException(Exception e) {
    LOGGER.error(e.getMessage(), e);
    return ResponseEntity
      .status(resolveStatus(e))
      .body(ImmutableMap.of("error", e.getMessage()));
  }

  private HttpStatus resolveStatus(Exception e) {
    return EXCEPTION_TO_STATUS.entrySet().stream().filter(
      entry -> entry.getKey().isAssignableFrom(e.getClass())
    ).map(Map.Entry::getValue).findFirst().orElse(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
