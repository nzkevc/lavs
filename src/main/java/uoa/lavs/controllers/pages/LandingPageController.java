package uoa.lavs.controllers.pages;

import javafx.scene.Parent;
import javafx.util.Pair;
import uoa.lavs.controllers.IController;
import uoa.lavs.utils.ResourceUtils;

public class LandingPageController implements IController {

  private static final Pair<Parent, LandingPageController> pair = ResourceUtils.loadFxml("pages/landing-page.fxml");

  public static Pair<Parent, LandingPageController> getPair() {
    return pair;
  }
}
