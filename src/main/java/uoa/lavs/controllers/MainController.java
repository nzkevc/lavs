package uoa.lavs.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import uoa.lavs.App;
import uoa.lavs.controllers.cards.ContactCardController;
import uoa.lavs.controllers.cards.EmployerCardController;
import uoa.lavs.controllers.cards.GeneralInfoCardController;
import uoa.lavs.controllers.cards.NoteCardController;
import uoa.lavs.controllers.pages.ExamplePageController;
import uoa.lavs.controllers.pages.LandingPageController;
import uoa.lavs.controllers.pages.SummaryPageController;
import uoa.lavs.utils.ControllerUtils;

public class MainController implements IController {

  Logger logger = LoggerFactory.getLogger(MainController.class);

  private static final int DEFAULT_WIDTH = 1920;
  private static final int DEFAULT_HEIGHT = 1080;
  private final Map<Class<?>, Parent> pages = new HashMap<>();

  private double zoom = 1;

  @FXML private Pane panLayout; // Responsible for responsiveness (contains everything)
  @FXML private Pane panPage; // Responsible for swapping pages (contains pages only)

  @FXML
  public void initialize() {
    setUpListeners();
    setUpPages();
  }

  public void switchPage(Class<?> page) {
    logger.debug("Switching to page: " + page.getSimpleName());
    ControllerUtils.swapComponent(panPage, pages.get(page));
  }

  private void setUpPages() {
    pages.put(LandingPageController.class, new LandingPageController());
    pages.put(ExamplePageController.class, new ExamplePageController());

    // These pages won't stay here but loading them for now to ensure they are working
    pages.put(GeneralInfoCardController.class, new GeneralInfoCardController());
    pages.put(EmployerCardController.class, new EmployerCardController());
    pages.put(ContactCardController.class, new ContactCardController());
    pages.put(NoteCardController.class, new NoteCardController());
    pages.put(SummaryPageController.class, new SummaryPageController());

    switchPage(SummaryPageController.class);
  }

  private void setUpListeners() {
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
