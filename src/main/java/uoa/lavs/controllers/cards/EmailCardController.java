package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Email;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.objects.ValidationException;

public class EmailCardController extends ICard<Email> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  private Integer number;
  @FXML private MFXTextField emailAddress;
  @FXML private MFXCheckbox isPrimary;

  @FXML
  private void initialize() {
    emailAddress.setTextLimit(60);
  }

  public EmailCardController() {
    ControllerUtils.loadFxml(this, "cards/email-card.fxml");
  }

  @Override
  public void render(Email email) {
    number = email.getNumber();
    emailAddress.setText(email.getAddress());
    isPrimary.setSelected(email.getIsPrimary());
  }

  @Override
  public void clear() {
    number = 0;
    emailAddress.clear();
    isPrimary.setSelected(false);
  }

  public void validate() throws ValidationException {
    try {
      Email.validateAddress(emailAddress.getText());
    } catch (ValidationException e) {
      throw new ValidationException("Email " + emailAddress.getText() + ": " + e.getMessage(), e);
    }
  }

  @Override
  public Email assemble() {
    Email email = new Email();
    email.setNumber(number);
    email.setAddress(emailAddress.getText());
    email.setIsPrimary(isPrimary.isSelected());
    return email;
  }
}
