package jp.gr.java_conf.saka.plantuml.viewer.repository.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlantUmlEntity {

  private String id;

  private String uml;

  private byte[] data;

  private long lastUpdate;
}
