package jp.gr.java_conf.saka.plantuml.viewer.web.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import jp.gr.java_conf.saka.plantuml.viewer.service.IPlantUmlService;
import jp.gr.java_conf.saka.plantuml.viewer.web.form.UmlRequestForm;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/svg")
public class SvgController {

  private IPlantUmlService plantUmlService;

  public SvgController(IPlantUmlService plantUmlService) {
    this.plantUmlService = plantUmlService;
  }

  @PostMapping(value = "/svg/generate", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> generate(@RequestBody UmlRequestForm requestForm,
    BindingResult result)
    throws IOException {
    if (result.hasErrors()) {
      throw new IllegalArgumentException(result.getFieldError().toString());
    }
    return ResponseEntity.ok(
      IOUtils.toString(plantUmlService.toSvg(requestForm.getPlantUml()),
        StandardCharsets.UTF_8));
  }
}
