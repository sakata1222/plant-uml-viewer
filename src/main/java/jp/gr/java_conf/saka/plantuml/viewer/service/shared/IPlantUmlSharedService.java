package jp.gr.java_conf.saka.plantuml.viewer.service.shared;

import java.io.IOException;
import java.io.InputStream;

public interface IPlantUmlSharedService {

  InputStream toSvg(String plantUml) throws IOException;

  InputStream toPdf(String plantUml) throws IOException;
}
