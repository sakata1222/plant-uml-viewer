package jp.gr.java_conf.saka.plantuml.viewer.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import jp.gr.java_conf.saka.plantuml.viewer.repository.IPlantUmlPdfRepository;
import jp.gr.java_conf.saka.plantuml.viewer.repository.entity.PlantUmlEntity;
import jp.gr.java_conf.saka.plantuml.viewer.service.IPlantUmlPdfService;
import jp.gr.java_conf.saka.plantuml.viewer.service.shared.IPlantUmlSharedService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class PlantUmlPdfService implements IPlantUmlPdfService {

  private IPlantUmlPdfRepository pdfRepository;
  private IPlantUmlSharedService plantUmlSharedService;

  public PlantUmlPdfService(
    IPlantUmlPdfRepository pdfRepository,
    IPlantUmlSharedService plantUmlSharedService) {
    this.pdfRepository = pdfRepository;
    this.plantUmlSharedService = plantUmlSharedService;
  }

  @Override
  public String createPdf(String uml) throws IOException {
    try (InputStream pdf = plantUmlSharedService.toPdf(uml)) {
      byte[] bytes = IOUtils.toByteArray(pdf);
      PlantUmlEntity entity = PlantUmlEntity.builder().uml(uml).data(bytes).build();
      return pdfRepository.create(entity).getId();
    }
  }

  @Override
  public Map<String, byte[]> findAllPdf() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public Optional<byte[]> findPdf(String id) {
    return pdfRepository.find(id).map(PlantUmlEntity::getData);
  }

}
