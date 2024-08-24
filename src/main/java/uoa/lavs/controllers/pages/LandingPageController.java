package uoa.lavs.controllers.pages;

import javafx.fxml.FXML;
import uoa.lavs.App;
import uoa.lavs.utils.ControllerUtils;

public class LandingPageController extends IPage {

  public LandingPageController() {
    ControllerUtils.loadFxml(this, "pages/landing-page.fxml");
  }

  @FXML
  private void onCustomerBtnClick() {
    App.getMainController().switchPage(SummaryPageController.class);
  }
}
