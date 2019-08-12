package jp.gr.java_conf.saka.plantuml.viewer.controller.common;


import org.springframework.http.MediaType;

public class CustomMediaTypes {

  public static final String IMAGE_SVG_XML_VALUE = "image/svg+xml";

  public static final MediaType IMAGE_SVG_XML = MediaType.valueOf(IMAGE_SVG_XML_VALUE);

  private CustomMediaTypes() {
  }
}
