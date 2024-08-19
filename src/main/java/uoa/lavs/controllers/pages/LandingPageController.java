package uoa.lavs.controllers.pages;

import uoa.lavs.controllers.IController;
import uoa.lavs.utils.ResourceUtils;
import uoa.lavs.utils.objects.Component;

public class LandingPageController implements IController {

  private static final Component<LandingPageController> singleton =
      ResourceUtils.loadFxml("pages/landing-page.fxml");

  public static Component<LandingPageController> getSingleton() {
    return singleton;
  }
}
