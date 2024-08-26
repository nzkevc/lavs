package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import org.dizitart.no2.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ControllerUtils;

public class PhoneCardController extends ICard<Phone> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  private Integer number;
  @FXML private MFXTextField prefix;
  @FXML private MFXTextField phoneNumber;
  @FXML private MFXCheckbox isPrimary;
  @FXML private MFXCheckbox isSendText;

  public PhoneCardController() {
    ControllerUtils.loadFxml(this, "cards/phone-card.fxml");
  }

  @FXML
  private void initialize() {
    prefix.setTextLimit(10);
    phoneNumber.setTextLimit(20);
  }

  @Override
  public void render(Phone phone) {
    number = phone.getNumber();
    prefix.setText(phone.getPrefix());
    phoneNumber.setText(phone.getPhoneNumber());
    isPrimary.setSelected(phone.getPrimary());
    isSendText.setSelected(phone.getCanSendTxt());
  }

  @Override
  public void clear() {
    number = 0;
    prefix.clear();
    phoneNumber.clear();
    isPrimary.setSelected(false);
    isSendText.setSelected(false);
  }

  public void validate() throws ValidationException {
    try {
      Phone.validatePrefix(prefix.getText());
      Phone.validatePhoneNumber(phoneNumber.getText());
    } catch (ValidationException e) {
      throw new ValidationException("Phone " + phoneNumber + ": " + e.getMessage(), e);
    }
  }

  @Override
  public Phone assemble() {
    Phone phone = new Phone();
    phone.setNumber(number);
    phone.setPrefix(prefix.getText());
    phone.setPhoneNumber(phoneNumber.getText());
    phone.setPrimary(isPrimary.isSelected());
    phone.setCanSendTxt(isSendText.isSelected());
    return phone;
  }
}
