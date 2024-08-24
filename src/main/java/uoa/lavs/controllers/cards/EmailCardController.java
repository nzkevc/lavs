package uoa.lavs.controllers.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import uoa.lavs.models.Email;
import uoa.lavs.utils.ControllerUtils;

public class EmailCardController extends ICard<Email> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  @FXML private MFXTextField  emailAddress;
  @FXML private MFXCheckbox isPrimary;

  public EmailCardController() {
    ControllerUtils.loadFxml(this, "cards/email-card.fxml");
  }

  @Override
  public void render(Email email) {
    emailAddress.setText(email.getAddress());
    isPrimary.setSelected(email.getIsPrimary());
  }

  @Override
  public void clear() {
    emailAddress.clear();
    isPrimary.setSelected(false);
  }

  @Override
  public Email assemble() {
    Email email = new Email();
    email.setAddress(emailAddress.getText());
    email.setIsPrimary(isPrimary.isSelected());
    return email;
  }
}
