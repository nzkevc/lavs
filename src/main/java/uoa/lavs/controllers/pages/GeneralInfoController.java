package uoa.lavs.controllers.pages;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.utils.ControllerUtils;

public class GeneralInfoController extends AnchorPane implements IController {

  @FXML private FieldController firstName;
  @FXML private FieldController lastName;
  @FXML private FieldController dateOfBirth;
  @FXML private FieldController citizenship;
  @FXML private FieldController visaType;
  @FXML private FieldController occupation;
  @FXML private FieldController status;

  public GeneralInfoController() {
    ControllerUtils.loadFxml(this, "fragments/cards/customer/general-info.fxml");
  }
}
