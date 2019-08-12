package jp.gr.java_conf.saka.plantuml.viewer.filter;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class LoggingFilter extends OncePerRequestFilter {

  private static final Logger LOGGER = LoggerFactory
    .getLogger(MethodHandles.lookup().lookupClass());

  @Override
  protected void doFilterInternal(HttpServletRequest request,
    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    LOGGER.info("REQUEST");
    LOGGER.info("  " + request.getMethod() + " " + request.getRequestURI());

    // execute
    filterChain.doFilter(request, response);

    LOGGER.info("RESPONSE");
    LOGGER.info("  " + response.getStatus());
  }
}
