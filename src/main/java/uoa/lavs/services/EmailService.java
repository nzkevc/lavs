package uoa.lavs.services;

import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Emails;
import uoa.lavs.repository.EmailRepository;
import uoa.lavs.repository.EmailsRepository;
import uoa.lavs.utils.objects.ValidationException;

class EmailService implements IService {
  public static void createEmailsFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Emails emails = newCustomer.getEmails();
    emails.setCustomerId(newCustomer.getId());
    for (Email email : emails.getEmails()) {
      email.setCustomerId(newCustomer.getId());
      EmailRepository.create(email);
    }
  }

  public static void updateEmailsFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Emails emails = newCustomer.getEmails();
    emails.setCustomerId(newCustomer.getId());
    for (Email email : emails.getEmails()) {
      email.setCustomerId(newCustomer.getId());
      if (email.getNumber() == null || email.getNumber() == 0) {
        EmailRepository.create(email);
      } else {
        EmailRepository.update(email);
      }
    }
  }

  public static Emails getEmails(Customer customer) throws RuntimeException {
    return EmailsRepository.get(customer);
  }
}
