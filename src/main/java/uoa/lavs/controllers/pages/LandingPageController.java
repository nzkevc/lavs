package uoa.lavs.controllers.pages;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.MainController;
import uoa.lavs.utils.ControllerUtils;

public class LandingPageController extends AnchorPane implements IController {

  public LandingPageController() {
    ControllerUtils.loadFxml(this, "pages/landing-page.fxml");
  }

  @FXML
  private void onCustomerBtnClick() {
    MainController.getInstance().switchPage(SummaryPageController.class);
  }
}
