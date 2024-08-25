package uoa.lavs.controllers.cards;

import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Address;
import uoa.lavs.models.Email;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ControllerUtils;

public class EmployerCardController extends ICard<Employer> {

  private static final Logger logger = LoggerFactory.getLogger(EmployerCardController.class);

  private Integer number;

  @FXML private MFXTextField employerName;
  @FXML private MFXCheckbox isOwner;

  @FXML private MFXTextField addressLine1;
  @FXML private MFXTextField addressLine2;
  @FXML private MFXTextField suburb;
  @FXML private MFXTextField city;
  @FXML private MFXTextField postcode;
  @FXML private MFXTextField country;

  @FXML private MFXTextField prefix;
  @FXML private MFXTextField phoneNumber;

  @FXML private MFXTextField emailAddress;

  @FXML private MFXTextField website;

  public EmployerCardController() {
    ControllerUtils.loadFxml(this, "cards/employer-card.fxml");
  }

  @Override
  public void render(Employer employer) {
    ControllerUtils.renderText(employerName, employer.getName());
    isOwner.setSelected(employer.isOwner());
    number = employer.getNumber();

    Address address = employer.getAddress();
    ControllerUtils.renderText(addressLine1, address.getLine1());
    ControllerUtils.renderText(addressLine2, address.getLine2());
    ControllerUtils.renderText(suburb, address.getSuburb());
    ControllerUtils.renderText(city, address.getCity());
    ControllerUtils.renderText(postcode, address.getPostCode());
    ControllerUtils.renderText(country, address.getCountry());

    Phone phone = employer.getPhone();
    ControllerUtils.renderText(prefix, phone.getPrefix());
    ControllerUtils.renderText(phoneNumber, phone.getPhoneNumber());

    Email email = employer.getEmail();
    ControllerUtils.renderText(emailAddress, email.getAddress());

    ControllerUtils.renderText(website, employer.getWebsite());
  }

  @Override
  public void clear() {
    number = 0;
    employerName.clear();
    isOwner.setSelected(false);

    addressLine1.clear();
    addressLine2.clear();
    suburb.clear();
    city.clear();
    postcode.clear();
    country.clear();

    prefix.clear();
    phoneNumber.clear();

    emailAddress.clear();

    website.clear();
  }

  @Override
  public Employer assemble() {
    Employer employer = new Employer();
    employer.setNumber(number);
    employer.setName(employerName.getText());
    employer.setOwner(isOwner.isSelected());

    Address address = new Address();
    address.setLine1(addressLine1.getText());
    address.setLine2(addressLine2.getText());
    address.setSuburb(suburb.getText());
    address.setCity(city.getText());
    address.setPostCode(postcode.getText());
    address.setCountry(country.getText());
    employer.setAddress(address);

    Phone phone = new Phone();
    phone.setPrefix(prefix.getText());
    phone.setPhoneNumber(phoneNumber.getText());
    employer.setPhone(phone);

    Email email = new Email();
    email.setAddress(emailAddress.getText());
    employer.setEmail(email);

    employer.setWebsite(website.getText());

    return employer;
  }
}
