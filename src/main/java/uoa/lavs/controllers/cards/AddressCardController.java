package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Address;
import uoa.lavs.utils.ControllerUtils;

public class AddressCardController extends ICard<Address> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  @FXML private FieldController addressLine1;
  @FXML private FieldController addressLine2;
  @FXML private FieldController suburb;
  @FXML private FieldController city;
  @FXML private FieldController postcode;
  @FXML private FieldController country;

  public AddressCardController() {
    ControllerUtils.loadFxml(this, "cards/address-card.fxml");
  }

  @Override
  public void render(Address address) {
    addressLine1.setValue(address.getLine1());
    addressLine2.setValue(address.getLine2());
    suburb.setValue(address.getSuburb());
    city.setValue(address.getCity());
    postcode.setValue(address.getPostCode());
    country.setValue(address.getCountry());
  }

  @Override
  public void clear() {
    addressLine1.clearValue();
    addressLine2.clearValue();
    suburb.clearValue();
    city.clearValue();
    postcode.clearValue();
    country.clearValue();
  }

  @Override
  public Address assemble() {
    Address address = new Address();
    address.setLine1(addressLine1.getValue());
    address.setLine2(addressLine2.getValue());
    address.setSuburb(suburb.getValue());
    address.setCity(city.getValue());
    address.setPostCode(postcode.getValue());
    address.setCountry(country.getValue());
    address.setIsPrimary(true);
    address.setIsMailing(true);
    return address;
  }
}
