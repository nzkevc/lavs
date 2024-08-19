package uoa.lavs.controllers.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.utils.ControllerUtils;

public class GeneralInfoController extends AnchorPane implements IController {

  private static final Logger logger = LoggerFactory.getLogger(GeneralInfoController.class);

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
