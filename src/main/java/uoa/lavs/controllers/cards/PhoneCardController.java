package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ControllerUtils;

public class PhoneCardController extends ICard<Phone> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  @FXML private FieldController prefix;
  @FXML private FieldController phoneNumber;

  public PhoneCardController() {
    ControllerUtils.loadFxml(this, "cards/phone-card.fxml");
  }

  @Override
  public void render(Phone phone) {
    prefix.setValue(phone.getPrefix());
    phoneNumber.setValue(phone.getPhoneNumber());
  }

  @Override
  public void clear() {
    prefix.clearValue();
    phoneNumber.clearValue();
  }

  @Override
  public Phone assemble() {
    Phone phone = new Phone();
    phone.setPrefix(prefix.getValue());
    phone.setPhoneNumber(phoneNumber.getValue());
    phone.setPrimary(true);
    phone.setCanSendTxt(true);
    return phone;
  }
}
