package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.cards.ContactCardController.ContactTriple;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Address;
import uoa.lavs.models.Email;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ControllerUtils;

public class ContactCardController extends AnchorPane implements ICard<ContactTriple> {

  // Temporary, will probably be removed after demo
  public static class ContactTriple {
    Address address;
    Phone phone;
    Email email;

    public ContactTriple(Address address, Phone phone, Email email) {
      this.address = address;
      this.phone = phone;
      this.email = email;
    }

    public Address getAddress() {
      return address;
    }

    public Phone getPhone() {
      return phone;
    }

    public Email getEmail() {
      return email;
    }
  }

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

  @Override
  public void render(ContactTriple contacts) {
    Address address = contacts.address;
    addressLine1.setValue(address.getLine1());
    suburb.setValue(address.getSuburb());
    city.setValue(address.getCity());
    postcode.setValue(address.getPostCode());
    country.setValue(address.getCountry());

    Phone phone = contacts.phone;
    prefix.setValue(phone.getPrefix());
    phoneNumber.setValue(phone.getPhoneNumber());

    Email email = contacts.email;
    emailAddress.setValue(email.getAddress());
  }

  @Override
  public void clear() {
    addressLine1.clearValue();
    suburb.clearValue();
    city.clearValue();
    postcode.clearValue();
    country.clearValue();
    prefix.clearValue();
    phoneNumber.clearValue();
    emailAddress.clearValue();
  }

  @Override
  public ContactTriple assemble() {
    return new ContactTriple(assembleAddress(), assemblePhone(), assembleEmail());
  }

  private Address assembleAddress() {
    Address address = new Address();
    address.setLine1(addressLine1.getValue());
    address.setSuburb(suburb.getValue());
    address.setCity(city.getValue());
    address.setPostCode(postcode.getValue());
    address.setCountry(country.getValue());
    return address;
  }

  private Phone assemblePhone() {
    Phone phone = new Phone();
    phone.setPrefix(prefix.getValue());
    phone.setPhoneNumber(phoneNumber.getValue());
    return phone;
  }

  private Email assembleEmail() {
    Email email = new Email();
    email.setAddress(emailAddress.getValue());
    return email;
  }
}
