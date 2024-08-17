package uoa.lavs;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.MainController;
import uoa.lavs.mainframe.simulator.NitriteConnection;
import uoa.lavs.utils.AsyncUtils;
import uoa.lavs.utils.ResourceUtils;
import uoa.lavs.utils.objects.ConnectionInstance;

/**
 * Initialisation and entry point of the application. Run using the play button or via `mvn clean
 * javafx:run`.
 */
public class App extends Application {

  private static final Logger logger = LoggerFactory.getLogger(App.class);
  private static Stage stage;
  private static Scene scene;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {
    logger.info("Starting application");
    App.stage = stage;

    // Load scene
    scene = new Scene(new AnchorPane(), 640, 480); // Temporary values
    stage.setScene(scene);
    scene.setRoot(MainController.getSingleton().getView());

    // Load resources
    ResourceUtils.loadFont("Montserrat-Medium.ttf");
    scene.getStylesheets().add(ResourceUtils.loadCss("globals.css"));

    // Set up stage
    scene.setFill(Color.web("#131d23"));
    stage.setTitle("Countrywide Bank: Loan System");
    stage.getIcons().add(ResourceUtils.loadImage("countrywide-bank-logo.png"));

    // Set up fullscreen
    stage.setMaximized(true);
    stage.setFullScreen(true);
    stage.setFullScreenExitHint("Press F11 to exit full screen mode.");
    scene.setOnKeyPressed(
        event -> {
          if (event.getCode() == KeyCode.F11) stage.setFullScreen(!stage.isFullScreen());
        });

    // Set up application
    ConnectionInstance.setConnection(new NitriteConnection("testing/nitrite.db"));
    State.reset();

    stage.show();
  }

  @Override
  public void stop() {
    logger.info("Stopping application");
    AsyncUtils.close();
  }

  public static Stage getStage() {
    return stage;
  }

  public static Scene getScene() {
    return scene;
  }
}
