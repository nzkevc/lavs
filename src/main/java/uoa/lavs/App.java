package uoa.lavs;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ResourceUtils;

/**
 * Initialisation and entry point of the application. Run using the play button or via `mvn clean
 * javafx:run`.
 */
public class App extends Application {

  private static final Logger logger = LoggerFactory.getLogger(App.class);

  @Override
  public void start(Stage stage) throws IOException {
    Scene scene = new Scene(ResourceUtils.loadFXML("main"), 640, 480);
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop() {
    AsyncUtils.close();
  }

  public static void main(String[] args) {
    launch();
  }
}
