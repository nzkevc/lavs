package uoa.lavs.controllers.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.utils.ControllerUtils;

public class GeneralInfoCardController extends AnchorPane implements IController {

  private static final Logger logger = LoggerFactory.getLogger(GeneralInfoCardController.class);

  @FXML private FieldController firstName;
  @FXML private FieldController lastName;
  @FXML private FieldController dateOfBirth;
  @FXML private FieldController citizenship;
  @FXML private FieldController visaType;
  @FXML private FieldController occupation;
  @FXML private FieldController status;

  public GeneralInfoCardController() {
    ControllerUtils.loadFxml(this, "cards/customer/general-info.fxml");
  }
}
