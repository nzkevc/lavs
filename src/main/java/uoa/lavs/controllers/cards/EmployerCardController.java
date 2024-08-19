package uoa.lavs.controllers.cards;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import uoa.lavs.controllers.fragments.FieldController;
import uoa.lavs.models.Address;
import uoa.lavs.models.Email;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ControllerUtils;

public class EmployerCardController extends AnchorPane implements ICard<Employer> {

  private static final Logger logger = LoggerFactory.getLogger(EmployerCardController.class);

  @FXML private FieldController employerName;
  @FXML private FieldController address;
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
    address.setValue(employerAddress.getLine1()); // Need line 2?
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
    address.clearValue();
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
    employerAddress.setLine1(address.getValue());
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
