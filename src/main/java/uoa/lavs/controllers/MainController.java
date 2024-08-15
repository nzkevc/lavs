package uoa.lavs.controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.State;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.ResourceUtils;

public class MainController implements IController {

  Logger logger = LoggerFactory.getLogger(MainController.class);

  private static final Parent root = ResourceUtils.loadFxml("main.fxml");

  public static Parent getRoot() {
    return root;
  }

  @FXML private Pane panPage;

  @FXML
  public void initialize() {
    // Bind page
    State.page.addListener(
        (observable, oldPage, newPage) -> {
          logger.debug("Page changed to: " + newPage);
          ControllerUtils.swapComponent(panPage, newPage);
        });
  }
}
