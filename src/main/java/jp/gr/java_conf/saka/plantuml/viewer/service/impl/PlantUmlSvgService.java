package jp.gr.java_conf.saka.plantuml.viewer.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import jp.gr.java_conf.saka.plantuml.viewer.repository.IPlantUmlSvgRepository;
import jp.gr.java_conf.saka.plantuml.viewer.repository.entity.PlantUmlEntity;
import jp.gr.java_conf.saka.plantuml.viewer.service.IPlantUmlSvgService;
import jp.gr.java_conf.saka.plantuml.viewer.service.shared.IPlantUmlSharedService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class PlantUmlSvgService implements IPlantUmlSvgService {

  private IPlantUmlSvgRepository svgRepository;
  private IPlantUmlSharedService plantUmlSharedService;

  public PlantUmlSvgService(
    IPlantUmlSvgRepository svgRepository,
    IPlantUmlSharedService plantUmlSharedService) {
    this.svgRepository = svgRepository;
    this.plantUmlSharedService = plantUmlSharedService;
  }

  @Override
  public String createSvg(String uml) throws IOException {
    try (InputStream svg = plantUmlSharedService.toSvg(uml)) {
      byte[] bytes = IOUtils.toByteArray(svg);
      PlantUmlEntity entity = PlantUmlEntity.builder().uml(uml).data(bytes).build();
      return svgRepository.create(entity).getId();
    }
  }

  @Override
  public String updateSvg(String id, String uml) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public Map<String, byte[]> findAllSvg() {
    return svgRepository.findAll().entrySet().stream()
      .collect(Collectors.toMap(
        Map.Entry::getKey,
        entry -> entry.getValue().getData()
      ));
  }

  @Override
  public Optional<byte[]> findSvg(String id) {
    return svgRepository.find(id).map(PlantUmlEntity::getData);
  }
}
