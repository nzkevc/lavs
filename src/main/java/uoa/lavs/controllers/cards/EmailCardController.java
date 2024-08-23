package uoa.lavs.controllers.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Email;
import uoa.lavs.utils.ControllerUtils;

public class EmailCardController extends ICard<Email> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  @FXML private FieldController emailAddress;

  public EmailCardController() {
    ControllerUtils.loadFxml(this, "cards/email-card.fxml");
  }

  @Override
  public void render(Email email) {
    emailAddress.setValue(email.getAddress());
  }

  @Override
  public void clear() {
    emailAddress.clearValue();
  }

  @Override
  public Email assemble() {
    Email email = new Email();
    email.setAddress(emailAddress.getValue());
    email.setIsPrimary(true);
    return email;
  }
}
