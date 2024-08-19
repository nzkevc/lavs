package uoa.lavs.controllers.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.utils.ControllerUtils;

public class ContactCardController extends AnchorPane implements IController {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  @FXML private FieldController address;
  @FXML private FieldController suburb;
  @FXML private FieldController city;
  @FXML private FieldController postcode;
  @FXML private FieldController country;

  @FXML private FieldController prefix;
  @FXML private FieldController phone;

  @FXML private FieldController emailAddress;

  public ContactCardController() {
    ControllerUtils.loadFxml(this, "cards/customer/contact-info.fxml");
  }
}
