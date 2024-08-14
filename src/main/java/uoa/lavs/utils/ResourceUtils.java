package uoa.lavs.utils;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.App;

public class ResourceUtils {
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  public static Parent loadFXML(String fxml) throws IOException {
    logger.debug("Loading FXML: " + fxml);
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }
}
