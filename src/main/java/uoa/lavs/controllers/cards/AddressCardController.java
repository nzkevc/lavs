package uoa.lavs.controllers.cards;

import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import uoa.lavs.models.Address;
import uoa.lavs.utils.ControllerUtils;

public class AddressCardController extends ICard<Address> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

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
    addressLine1.setText(address.getLine1());
    addressLine2.setText(address.getLine2());
    suburb.setText(address.getSuburb());
    city.setText(address.getCity());
    postcode.setText(address.getPostCode());
    country.setText(address.getCountry());
    isPrimary.setSelected(address.getPrimary());
    isMailing.setSelected(address.getMailing());
  }

  @Override
  public void clear() {
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
