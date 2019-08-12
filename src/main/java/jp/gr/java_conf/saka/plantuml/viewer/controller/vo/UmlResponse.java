package jp.gr.java_conf.saka.plantuml.viewer.controller.vo;

import java.net.URI;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UmlResponse {

  @NotBlank
  private String id;

  @NotBlank
  private String plantUml;

  @NotBlank
  private String rawDataPath;

  @NotBlank
  private URI rawDataUrl;
}
