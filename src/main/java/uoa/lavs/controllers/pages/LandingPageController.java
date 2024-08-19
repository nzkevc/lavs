package uoa.lavs.controllers.pages;

import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;
import uoa.lavs.utils.ControllerUtils;

public class LandingPageController extends AnchorPane implements IController {

  public LandingPageController() {
    ControllerUtils.loadFxml(this, "pages/landing-page.fxml");
  }
}
