package uoa.lavs.controllers.cards;

import java.util.Set;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  @FXML private AddressCardController addressCard;
  @FXML private PhoneCardController phoneCard;
  @FXML private EmailCardController emailCard;

  public ContactCardController() {
    ControllerUtils.loadFxml(this, "cards/contact-card.fxml");
  }

  @Override
  public void render(ContactInfo contactInfo) {
    Set<Address> addresses = contactInfo.addresses().getAddresses();
    Set<Phone> phones = contactInfo.phones().getPhoneNumbers();
    Set<Email> emails = contactInfo.emails().getEmails();

    addressCard.render(addresses.iterator().next());
    phoneCard.render(phones.iterator().next());
    emailCard.render(emails.iterator().next());
  }

  @Override
  public void clear() {
    addressCard.clear();
    phoneCard.clear();
    emailCard.clear();
  }

  @Override
  public ContactInfo assemble() {
    Address address = addressCard.assemble();
    Phone phone = phoneCard.assemble();
    Email email = emailCard.assemble();

    Addresses addresses = new Addresses(Set.of(address));
    Phones phones = new Phones(Set.of(phone));
    Emails emails = new Emails(Set.of(email));

    return new ContactInfo(addresses, phones, emails);
  }
}
