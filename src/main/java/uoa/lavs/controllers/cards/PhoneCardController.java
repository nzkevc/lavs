package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ControllerUtils;

public class PhoneCardController extends ICard<Phone> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  @FXML private MFXTextField prefix;
  @FXML private MFXTextField phoneNumber;
  @FXML private MFXCheckbox isPrimary;
  @FXML private MFXCheckbox isSendText;
  

  public PhoneCardController() {
    ControllerUtils.loadFxml(this, "cards/phone-card.fxml");
  }

  @Override
  public void render(Phone phone) {
    prefix.setText(phone.getPrefix());
    phoneNumber.setText(phone.getPhoneNumber());
    isPrimary.setSelected(phone.getPrimary());
    isSendText.setSelected(phone.getCanSendTxt());
  }

  @Override
  public void clear() {
    prefix.clear();
    phoneNumber.clear();
    isPrimary.setSelected(false);
    isSendText.setSelected(false);
  }

  @Override
  public Phone assemble() {
    Phone phone = new Phone();
    phone.setPrefix(prefix.getText());
    phone.setPhoneNumber(phoneNumber.getText());
    phone.setPrimary(isPrimary.isSelected());
    phone.setCanSendTxt(isSendText.isSelected());
    return phone;
  }
}
