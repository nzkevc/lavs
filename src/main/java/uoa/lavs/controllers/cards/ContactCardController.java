package uoa.lavs.controllers.cards;

import java.util.Set;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.controllers.fragments.ScrollerController;
import uoa.lavs.models.Address;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Email;
import uoa.lavs.models.Emails;
import uoa.lavs.models.Phone;
import uoa.lavs.models.Phones;
import uoa.lavs.utils.ControllerUtils;
import uoa.lavs.utils.objects.ContactInfo;

public class ContactCardController extends ICard<ContactInfo> {

  private static final Logger logger = LoggerFactory.getLogger(ContactCardController.class);

  @FXML private ScrollerController<Address> addressCards;
  @FXML private ScrollerController<Phone> phoneCards;
  @FXML private ScrollerController<Email> emailCards;

  public ContactCardController() {
    ControllerUtils.loadFxml(this, "cards/contact-card.fxml");
  }

  @FXML
  public void initialize() {
    addressCards.setCardController(AddressCardController.class);
    phoneCards.setCardController(PhoneCardController.class);
    emailCards.setCardController(EmailCardController.class);
  }

  @Override
  public void render(ContactInfo contactInfo) {
    Set<Address> addresses = contactInfo.addresses().getAddresses();
    Set<Phone> phones = contactInfo.phones().getPhoneNumbers();
    Set<Email> emails = contactInfo.emails().getEmails();
    addressCards.render(addresses);
    phoneCards.render(phones);
    emailCards.render(emails);
  }

  @Override
  public void clear() {
    addressCards.clear();
    phoneCards.clear();
    emailCards.clear();
  }

  @Override
  public ContactInfo assemble() {
    Addresses addresses = new Addresses(addressCards.assemble());
    Phones phones = new Phones(phoneCards.assemble());
    Emails emails = new Emails(emailCards.assemble());
    return new ContactInfo(addresses, phones, emails);
  }
}
