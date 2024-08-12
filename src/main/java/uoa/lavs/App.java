package uoa.lavs;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uoa.lavs.utils.AsyncUtils;

/**
 * Initialisation and entry point of the application. Run using the play button or via `mvn clean
 * javafx:run`.
 */
public class App extends Application {

  private static Scene scene;

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  @Override
  public void start(Stage stage) throws IOException {
    scene = new Scene(loadFXML("example"), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  private static Parent loadFXML(String fxml) throws IOException {
    logger.debug("Loading FXML: " + fxml);
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }

  @Override
  public void stop() {
    AsyncUtils.close();
  }

  public static void main(String[] args) {
    launch();
  }
}
