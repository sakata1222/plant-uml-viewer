package jp.gr.java_conf.saka.plantuml.viewer.repository;

import java.util.Map;
import java.util.Optional;
import jp.gr.java_conf.saka.plantuml.viewer.repository.entity.PlantUmlEntity;

public interface IPlantUmlSvgRepository {

  PlantUmlEntity create(PlantUmlEntity umlEntity);

  PlantUmlEntity update(PlantUmlEntity umlEntity);

  Map<String, PlantUmlEntity> findAll();

  Optional<PlantUmlEntity> find(String id);
}
