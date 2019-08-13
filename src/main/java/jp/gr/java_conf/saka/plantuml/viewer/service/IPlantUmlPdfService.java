package jp.gr.java_conf.saka.plantuml.viewer.service;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public interface IPlantUmlPdfService {

  String createPdf(String uml) throws IOException;

  Map<String, byte[]> findAllPdf();

  Optional<byte[]> findPdf(String id);
}
