package uoa.lavs.controllers.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Address;
import uoa.lavs.models.Email;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ControllerUtils;

public class EmployerCardController extends ICard<Employer> {

  private static final Logger logger = LoggerFactory.getLogger(EmployerCardController.class);

  @FXML private FieldController employerName;
  @FXML private FieldController addressLine1;
  @FXML private FieldController addressLine2;
  @FXML private FieldController suburb;
  @FXML private FieldController city;
  @FXML private FieldController postcode;
  @FXML private FieldController country;

  @FXML private FieldController phone;
  @FXML private FieldController emailAddress;
  @FXML private FieldController website;

  public EmployerCardController() {
    ControllerUtils.loadFxml(this, "cards/employer-card.fxml");
  }

  @Override
  public void render(Employer employer) {
    employerName.setValue(employer.getName());

    Address employerAddress = employer.getAddress();
    addressLine1.setValue(employerAddress.getLine1());
    addressLine2.setValue(employerAddress.getLine2());
    suburb.setValue(employerAddress.getSuburb());
    city.setValue(employerAddress.getCity());
    postcode.setValue(employerAddress.getPostCode());
    country.setValue(employerAddress.getCountry());

    Phone employerPhone = employer.getPhone();
    phone.setValue(employerPhone.getFullNumber()); // Check

    Email employerEmail = employer.getEmail();
    emailAddress.setValue(employerEmail.getAddress());

    website.setValue(employer.getWebsite());
  }

  @Override
  public void clear() {
    employerName.clearValue();
    addressLine1.clearValue();
    addressLine2.clearValue();
    suburb.clearValue();
    city.clearValue();
    postcode.clearValue();
    country.clearValue();
    phone.clearValue();
    emailAddress.clearValue();
    website.clearValue();
  }

  @Override
  public Employer assemble() {
    Employer employer = new Employer();
    employer.setName(employerName.getValue());

    Address employerAddress = new Address();
    employerAddress.setLine1(addressLine1.getValue());
    employerAddress.setLine2(addressLine2.getValue());
    employerAddress.setSuburb(suburb.getValue());
    employerAddress.setCity(city.getValue());
    employerAddress.setPostCode(postcode.getValue());
    employerAddress.setCountry(country.getValue());
    employer.setAddress(employerAddress);

    Phone employerPhone = new Phone();
    employerPhone.setPhoneNumber(phone.getValue());
    employer.setPhone(employerPhone);

    Email employerEmail = new Email();
    employerEmail.setAddress(emailAddress.getValue());
    employer.setEmail(employerEmail);

    employer.setWebsite(website.getValue());
    return employer;
  }
}
