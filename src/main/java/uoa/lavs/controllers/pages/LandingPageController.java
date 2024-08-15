package uoa.lavs.controllers.pages;

import javafx.scene.Parent;
import uoa.lavs.controllers.IController;
import uoa.lavs.utils.ResourceUtils;

public class LandingPageController implements IController {

  private static final Parent root = ResourceUtils.loadFxml("pages/landing-page.fxml");

  public static Parent getRoot() {
    return root;
  }
}
