package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Address;
import uoa.lavs.utils.ControllerUtils;

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
