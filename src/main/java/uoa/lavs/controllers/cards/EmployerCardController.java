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

  @FXML private MFXTextField employerName;
  @FXML private MFXCheckbox isOwner;

  @FXML private MFXTextField addressLine1;
  @FXML private MFXTextField addressLine2;
  @FXML private MFXTextField suburb;
  @FXML private MFXTextField city;
  @FXML private MFXTextField postcode;
  @FXML private MFXTextField country;
  @FXML private MFXCheckbox isPrimary;
  @FXML private MFXCheckbox isMailing;

  @FXML private MFXTextField prefix;
  @FXML private MFXTextField phoneNumber;
  @FXML private MFXCheckbox isPhonePrimary;
  @FXML private MFXCheckbox isPhoneSendText;

  @FXML private MFXTextField emailAddress;
  @FXML private MFXCheckbox isEmailPrimary;

  @FXML private MFXTextField website;

  public EmployerCardController() {
    ControllerUtils.loadFxml(this, "cards/employer-card.fxml");
  }

  @Override
  public void render(Employer employer) {
    employerName.setText(employer.getName());
    isOwner.setSelected(employer.isOwner());

    Address address = employer.getAddress();
    addressLine1.setText(address.getLine1());
    addressLine2.setText(address.getLine2());
    suburb.setText(address.getSuburb());
    city.setText(address.getCity());
    postcode.setText(address.getPostCode());
    country.setText(address.getCountry());
    isPrimary.setSelected(address.getPrimary());
    isMailing.setSelected(address.getMailing());

    Phone phone = employer.getPhone();
    prefix.setText(phone.getPrefix());
    phoneNumber.setText(phone.getPhoneNumber());
    isPhonePrimary.setSelected(phone.getPrimary());
    isPhoneSendText.setSelected(phone.getCanSendTxt());

    Email email = employer.getEmail();
    emailAddress.setText(email.getAddress());
    isEmailPrimary.setSelected(email.getIsPrimary());

    website.setText(employer.getWebsite());
  }

  @Override
  public void clear() {
    employerName.clear();
    isOwner.setSelected(false);

    addressLine1.clear();
    addressLine2.clear();
    suburb.clear();
    city.clear();
    postcode.clear();
    country.clear();
    isPrimary.setSelected(false);
    isMailing.setSelected(false);

    prefix.clear();
    phoneNumber.clear();
    isPhonePrimary.setSelected(false);
    isPhoneSendText.setSelected(false);

    emailAddress.clear();
    isEmailPrimary.setSelected(false);

    website.clear();
  }

  @Override
  public Employer assemble() {

    Employer employer = new Employer();
    employer.setName(employerName.getText());
    employer.setOwner(isOwner.isSelected());

    Address address = new Address();
    address.setLine1(addressLine1.getText());
    address.setLine2(addressLine2.getText());
    address.setSuburb(suburb.getText());
    address.setCity(city.getText());
    address.setPostCode(postcode.getText());
    address.setCountry(country.getText());
    address.setIsPrimary(isPrimary.isSelected());
    address.setIsMailing(isMailing.isSelected());
    employer.setAddress(address);

    Phone phone = new Phone();
    phone.setPrefix(prefix.getText());
    phone.setPhoneNumber(phoneNumber.getText());
    phone.setPrimary(isPhonePrimary.isSelected());
    phone.setCanSendTxt(isPhoneSendText.isSelected());
    employer.setPhone(phone);

    Email email = new Email();
    email.setAddress(emailAddress.getText());
    email.setIsPrimary(isEmailPrimary.isSelected());
    employer.setEmail(email);

    employer.setWebsite(website.getText());

    return employer;
  }
}
