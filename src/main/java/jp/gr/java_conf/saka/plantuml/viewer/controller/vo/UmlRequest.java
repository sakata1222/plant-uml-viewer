package jp.gr.java_conf.saka.plantuml.viewer.controller.vo;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UmlRequest {

  @NotBlank
  private String plantUml;
}
