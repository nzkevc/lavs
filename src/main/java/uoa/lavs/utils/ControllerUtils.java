package uoa.lavs.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import uoa.lavs.App;
import uoa.lavs.controllers.IController;

public class ControllerUtils {

  private static final Logger logger = LoggerFactory.getLogger(ControllerUtils.class);

  public static void swapComponent(Pane parent, Node newChild) {
    parent.getChildren().clear();
    parent.getChildren().add(newChild);
  }

  /**
   * Load an FXML file into a controller. Call this in the constructor of the controller.
   *
   * @param controller the controller to load the FXML into (pass in `this`)
   */
  public static void loadFxml(IController controller, String url) {
    logger.debug("Loading FXML: " + url);
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + url));
    fxmlLoader.setRoot(controller);
    fxmlLoader.setController(controller);

    try {
      fxmlLoader.load();
    } catch (IOException e) {
      logger.error("Error loading FXML", e);
    }
  }
}
