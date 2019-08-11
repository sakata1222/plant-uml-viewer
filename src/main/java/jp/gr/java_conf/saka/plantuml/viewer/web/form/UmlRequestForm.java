package jp.gr.java_conf.saka.plantuml.viewer.web.form;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UmlRequestForm {

  @NotBlank
  private String plantUml;
}
