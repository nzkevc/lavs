package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Address;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.objects.ValidationException;

public class AddressCardController extends ICard<Address> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  private Integer number;
  @FXML private MFXTextField addressLine1;
  @FXML private MFXTextField addressLine2;
  @FXML private MFXTextField suburb;
  @FXML private MFXTextField city;
  @FXML private MFXTextField postcode;
  @FXML private MFXTextField country;
  @FXML private MFXCheckbox isPrimary;
  @FXML private MFXCheckbox isMailing;

  public AddressCardController() {
    ControllerUtils.loadFxml(this, "cards/address-card.fxml");
  }

  @FXML
  private void initialize() {
    addressLine1.setTextLimit(60);
    addressLine2.setTextLimit(60);
    suburb.setTextLimit(30);
    city.setTextLimit(30);
    postcode.setTextLimit(10);
    // TODO: set postcode to only numbers
    country.setTextLimit(30);
  }

  @Override
  public void render(Address address) {
    number = address.getNumber();
    ControllerUtils.renderText(addressLine1, address.getLine1());
    ControllerUtils.renderText(addressLine2, address.getLine2());
    ControllerUtils.renderText(suburb, address.getSuburb());
    ControllerUtils.renderText(city, address.getCity());
    ControllerUtils.renderText(postcode, address.getPostCode());
    ControllerUtils.renderText(country, address.getCountry());
    isPrimary.setSelected(address.getPrimary());
    isMailing.setSelected(address.getMailing());
  }

  @Override
  public void clear() {
    number = 0;
    addressLine1.clear();
    addressLine2.clear();
    suburb.clear();
    city.clear();
    postcode.clear();
    country.clear();
    isPrimary.setSelected(false);
    isMailing.setSelected(false);
  }

  @Override
  public void validate() throws ValidationException {
    try {
      Address.validateLine1(addressLine1.getText());
      Address.validateLine2(addressLine2.getText());
      Address.validateSuburb(suburb.getText());
      Address.validateCity(city.getText());
      Address.validatePostcode(postcode.getText());
      Address.validateCountry(country.getText());
    } catch (ValidationException e) {
      throw new ValidationException("Address " + addressLine1 + ": " + e.getMessage(), e);
    }
  }

  @Override
  public Address assemble() {
    Address address = new Address();
    address.setNumber(number);
    address.setLine1(addressLine1.getText());
    address.setLine2(addressLine2.getText());
    address.setSuburb(suburb.getText());
    address.setCity(city.getText());
    address.setPostCode(postcode.getText());
    address.setCountry(country.getText());
    address.setIsPrimary(isPrimary.isSelected());
    address.setIsMailing(isMailing.isSelected());
    return address;
  }
}
