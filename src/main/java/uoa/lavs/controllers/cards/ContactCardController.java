package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.IController;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Address;
import uoa.lavs.models.Email;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ControllerUtils;

public class ContactCardController extends AnchorPane implements IController {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  @FXML private FieldController addressLine1;
  @FXML private FieldController suburb;
  @FXML private FieldController city;
  @FXML private FieldController postcode;
  @FXML private FieldController country;

  @FXML private FieldController prefix;
  @FXML private FieldController phoneNumber;

  @FXML private FieldController emailAddress;

  public ContactCardController() {
    ControllerUtils.loadFxml(this, "cards/customer/contact-info.fxml");
  }

  public void renderContacts(Address address, Phone phone, Email email) {
    addressLine1.setValue(address.getLine1());
    suburb.setValue(address.getSuburb());
    city.setValue(address.getCity());
    postcode.setValue(address.getPostCode());
    country.setValue(address.getCountry());

    prefix.setValue(phone.getPrefix());
    phoneNumber.setValue(phone.getPhoneNumber());

    emailAddress.setValue(email.getAddress());
  }

  public void clearFields() {
    addressLine1.clearValue();
    suburb.clearValue();
    city.clearValue();
    postcode.clearValue();
    country.clearValue();
    prefix.clearValue();
    phoneNumber.clearValue();
    emailAddress.clearValue();
  }

  public Address assembleAddress() {
    Address address = new Address();
    address.setLine1(addressLine1.getValue());
    address.setSuburb(suburb.getValue());
    address.setCity(city.getValue());
    address.setPostCode(postcode.getValue());
    address.setCountry(country.getValue());
    return address;
  }

  public Phone assemblePhone() {
    Phone phone = new Phone();
    phone.setPrefix(prefix.getValue());
    phone.setPhoneNumber(phoneNumber.getValue());
    return phone;
  }

  public Email assembleEmail() {
    Email email = new Email();
    email.setAddress(emailAddress.getValue());
    return email;
  }
}
