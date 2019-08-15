package jp.gr.java_conf.saka.plantuml.viewer.repository.impl;

import com.google.common.collect.Maps;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.function.LongSupplier;
import jp.gr.java_conf.saka.plantuml.viewer.repository.entity.PlantUmlEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PlantUmlInMemoryRepository {

  private LongSupplier currentTimeSupplier = System::currentTimeMillis;

  private Map<String, PlantUmlEntity> fifoQueue = new LinkedHashMap<String, PlantUmlEntity>() {
    @Override
    protected boolean removeEldestEntry(
      Entry<String, PlantUmlEntity> entry) {
      return size() > 100;
    }
  };

  public PlantUmlEntity create(
    PlantUmlEntity umlEntity) {
    umlEntity.setId(generateId());
    umlEntity.setLastUpdate(currentTimeSupplier.getAsLong());
    fifoQueue.put(umlEntity.getId(), umlEntity);
    return umlEntity;
  }

  public PlantUmlEntity update(
    PlantUmlEntity umlEntity) {
    umlEntity.setLastUpdate(currentTimeSupplier.getAsLong());
    fifoQueue.put(umlEntity.getId(), umlEntity);
    return umlEntity;
  }

  public Map<String, PlantUmlEntity> findAll() {
    return Maps.newLinkedHashMap(fifoQueue);
  }

  public Optional<PlantUmlEntity> find(String id) {
    return Optional.ofNullable(fifoQueue.getOrDefault(id, null));
  }

  private String generateId() {
    return UUID.randomUUID().toString();
  }
}
