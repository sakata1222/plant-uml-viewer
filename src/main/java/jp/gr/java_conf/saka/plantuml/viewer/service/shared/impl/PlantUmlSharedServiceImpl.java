package jp.gr.java_conf.saka.plantuml.viewer.service.shared.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.util.function.Function;
import jp.gr.java_conf.saka.plantuml.viewer.service.shared.IPlantUmlSharedService;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlantUmlSharedServiceImpl implements IPlantUmlSharedService {

  private static final Logger LOGGER = LoggerFactory
    .getLogger(MethodHandles.lookup().lookupClass());

  private Function<String, SourceStringReader> readerFactory;
  private PdfGenerator pdfGenerator;

  @Autowired
  public PlantUmlSharedServiceImpl(
    PdfGenerator pdfGenerator) {
    this(SourceStringReader::new, pdfGenerator);
  }

  public PlantUmlSharedServiceImpl(
    Function<String, SourceStringReader> readerFactory,
    PdfGenerator pdfGenerator) {
    this.readerFactory = readerFactory;
    this.pdfGenerator = pdfGenerator;
  }

  @Override
  public InputStream toSvg(String plantUml) throws IOException {
    return generate(plantUml, FileFormat.SVG);
  }

  @Override
  public InputStream toPdf(String plantUml) throws IOException {
    InputStream png = toPng(plantUml);
    return new ByteArrayInputStream(pdfGenerator.generateFromImage(IOUtils.toByteArray(png)));
  }

  private InputStream toPng(String plantUml) throws IOException {
    return generate(plantUml, FileFormat.PNG);
  }

  private InputStream generate(String plantUml, FileFormat format) throws IOException {
    LOGGER.info(plantUml);
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    readerFactory.apply(plantUml).generateImage(
      os, new FileFormatOption(format));
    return new ByteArrayInputStream(os.toByteArray());
  }
}
