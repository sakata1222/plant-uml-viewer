package jp.gr.java_conf.saka.plantuml.viewer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class HelloGradleController {

  @GetMapping
  public String helloGradle() {
    return "Hello Gradle!";
  }

}
