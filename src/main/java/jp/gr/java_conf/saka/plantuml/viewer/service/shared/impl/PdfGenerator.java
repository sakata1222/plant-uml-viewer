package jp.gr.java_conf.saka.plantuml.viewer.service.shared.impl;

import com.google.common.collect.ImmutableList;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;

@Component
public class PdfGenerator {

  private static final float PADDING_Y = 5;
  private static final float PADDING_X = 5;

  private final List<PDRectangle> SUPPORTED_PDF_SIZE = ImmutableList.<PDRectangle>builder()
    .add(PDRectangle.A4)
    .add(PDRectangle.A3)
    .add(PDRectangle.A2)
    .add(PDRectangle.A1)
    .build();

  public byte[] generateFromImage(byte[] imageData) throws IOException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try (PDDocument doc = new PDDocument()) {
      PDImageXObject image = PDImageXObject.createFromByteArray(doc, imageData, null);
      PDPage page = new PDPage(decideSize(image));
      doc.addPage(page);
      try (PDPageContentStream contentStream = new PDPageContentStream(doc, page)) {
        contentStream
          .drawImage(image, PADDING_X,
            page.getMediaBox().getHeight() - image.getHeight() - PADDING_Y);
      }
      doc.save(bos);
    }
    return bos.toByteArray();
  }

  private PDRectangle decideSize(PDImageXObject image) {
    return SUPPORTED_PDF_SIZE.stream().filter(size -> isInSize(size, image)).findFirst()
      .orElse(new PDRectangle(image.getWidth(), image.getHeight()));
  }

  private boolean isInSize(PDRectangle size, PDImageXObject image) {
    return size.getWidth() >= image.getWidth() + PADDING_X
      && size.getHeight() >= image.getHeight() + PADDING_Y;
  }
}
