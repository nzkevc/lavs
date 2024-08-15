package uoa.lavs.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.App;
import uoa.lavs.State;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.ResourceUtils;

public class MainController implements IController, Initializable {

  Logger logger = LoggerFactory.getLogger(MainController.class);

  private static final Parent root = ResourceUtils.loadFxml("main.fxml");
  private static final int DEFAULT_WIDTH = 1920;
  private static final int DEFAULT_HEIGHT = 1080;

  private double zoom = 1;

  public static Parent getRoot() {
    return root;
  }

  @FXML private Pane panResponsive; // Responsible for scaling the content
  @FXML private Pane panPage; // Responsible for swapping pages

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    // Page state listener
    State.page.addListener(
        (observable, oldPage, newPage) -> {
          logger.debug("Page changed to: " + newPage);
          ControllerUtils.swapComponent(panPage, newPage);
        });

    // Screen resize listeners
    Scene scene = App.getScene();
    scene
        .heightProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              updateOuterPaneSize(scene.getWidth(), newVal.doubleValue());
            });
    scene
        .widthProperty()
        .addListener(
            (obs, oldVal, newVal) -> {
              updateOuterPaneSize(newVal.doubleValue(), scene.getHeight());
            });
  }

  /**
   * Updates the screen dimensions to fill the scene. Will scale inner contents. Will maintain
   * original aspect ratio, adding borders if neccessary (screen centered).
   *
   * @param sceneWidth The current width of the scene.
   * @param sceneHeight The current height of the scene.
   */
  private void updateOuterPaneSize(double sceneWidth, double sceneHeight) {
    // Height to set outer pane to be (maintain aspect ratio)
    double aspectRatio = (double) DEFAULT_WIDTH / DEFAULT_HEIGHT;
    Double screenWidth = Math.min(sceneWidth, sceneHeight * aspectRatio);

    // Set outer pane size
    zoom = screenWidth / DEFAULT_WIDTH;
    panResponsive.setScaleX(zoom);
    panResponsive.setScaleY(zoom);

    // Center content
    double horizontalShift = (sceneWidth - DEFAULT_WIDTH) / 2;
    double verticalShift = (sceneHeight - DEFAULT_HEIGHT) / 2;
    panResponsive.setLayoutX(horizontalShift);
    panResponsive.setLayoutY(verticalShift);
  }
}
