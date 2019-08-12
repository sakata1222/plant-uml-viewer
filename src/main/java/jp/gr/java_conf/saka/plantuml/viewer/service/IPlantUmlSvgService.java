package jp.gr.java_conf.saka.plantuml.viewer.service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface IPlantUmlSvgService {

  String createSvg(String uml) throws IOException;

  String updateSvg(String id, String uml);

  Map<String, byte[]> findAllSvg();

  Optional<byte[]> findSvg(String id);
}
