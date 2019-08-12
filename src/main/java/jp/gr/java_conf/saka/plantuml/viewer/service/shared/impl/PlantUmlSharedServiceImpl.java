package jp.gr.java_conf.saka.plantuml.viewer.service.shared.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.function.Function;
import jp.gr.java_conf.saka.plantuml.viewer.service.shared.IPlantUmlSharedService;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlantUmlSharedServiceImpl implements IPlantUmlSharedService {

  private static final Logger LOGGER = LoggerFactory
    .getLogger(MethodHandles.lookup().lookupClass());

  private Function<String, SourceStringReader> readerFactory;

  public PlantUmlSharedServiceImpl() {
    this(SourceStringReader::new);
  }

  public PlantUmlSharedServiceImpl(
    Function<String, SourceStringReader> readerFactory) {
    this.readerFactory = readerFactory;
  }

  @Override
  public InputStream toSvg(String plantUml) throws IOException {
    return generate(plantUml, FileFormat.SVG);
  }

  @Override
  public InputStream toPdf(String plantUml) throws IOException {
    return generate(plantUml, FileFormat.PDF);
  }

  private InputStream generate(String plantUml, FileFormat format) throws IOException {
    LOGGER.info(plantUml);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    String description = readerFactory.apply(plantUml).generateImage(
      os, new FileFormatOption(format));
    if (Objects.isNull(description)) {
      throw new IllegalArgumentException(plantUml);
    }
    return new ByteArrayInputStream(os.toByteArray());
  }
}
