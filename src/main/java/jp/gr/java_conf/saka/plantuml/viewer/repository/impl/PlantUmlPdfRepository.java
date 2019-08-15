package jp.gr.java_conf.saka.plantuml.viewer.repository.impl;

import java.util.Map;
import java.util.Optional;
import jp.gr.java_conf.saka.plantuml.viewer.repository.IPlantUmlPdfRepository;
import jp.gr.java_conf.saka.plantuml.viewer.repository.entity.PlantUmlEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PlantUmlPdfRepository implements IPlantUmlPdfRepository {

  private PlantUmlInMemoryRepository repository;

  public PlantUmlPdfRepository(
    PlantUmlInMemoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public PlantUmlEntity create(
    PlantUmlEntity umlEntity) {
    return repository.create(umlEntity);
  }

  @Override
  public Map<String, PlantUmlEntity> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<PlantUmlEntity> find(String id) {
    return repository.find(id);
  }
}
