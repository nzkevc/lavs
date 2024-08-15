package uoa.lavs.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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

  @FXML private Pane panLayout; // Responsible for responsiveness (contains everything)
  @FXML private Pane panPage; // Responsible for swapping pages (contains pages only)

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    // Page state listener
    State.page.addListener(
        (observable, oldPage, newPage) -> {
          logger.debug("Page changed to: " + newPage.idProperty().get());
          ControllerUtils.swapComponent(panPage, newPage);
        });

    // Screen resize listeners
    Scene scene = App.getScene();
    scene
        .heightProperty()
        .addListener(
            (obs, oldHeight, newHeight) -> {
              updateLayoutSize(scene.getWidth(), newHeight.doubleValue());
            });
    scene
        .widthProperty()
        .addListener(
            (obs, oldWidth, newWidth) -> {
              updateLayoutSize(newWidth.doubleValue(), scene.getHeight());
            });
  }

  /**
   * Updates the layout dimensions to fill the scene. Will scale inner contents. Will maintain
   * original aspect ratio, adding borders if neccessary (layout centered).
   *
   * @param sceneWidth The current width of the scene.
   * @param sceneHeight The current height of the scene.
   */
  private void updateLayoutSize(double sceneWidth, double sceneHeight) {
    // Calculate layout width (which may be smaller than scene width to maintain aspect ratio)
    double aspectRatio = (double) DEFAULT_WIDTH / DEFAULT_HEIGHT;
    Double layoutWidth = Math.min(sceneWidth, sceneHeight * aspectRatio);

    // Calculate zoom and shift (to recentre)
    zoom = layoutWidth / DEFAULT_WIDTH;
    double horizontalShift = (sceneWidth - DEFAULT_WIDTH) / 2;
    double verticalShift = (sceneHeight - DEFAULT_HEIGHT) / 2;

    // Apply zoom and shift
    panLayout.setScaleX(zoom);
    panLayout.setScaleY(zoom);
    panLayout.setLayoutX(horizontalShift);
    panLayout.setLayoutY(verticalShift);
  }

  public double getZoom() {
    return zoom;
  }
}
