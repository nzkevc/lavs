package uoa.lavs.services;

import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Emails;
import uoa.lavs.repository.EmailRepository;
import uoa.lavs.repository.EmailsRepository;
import uoa.lavs.utils.objects.ValidationException;

class EmailService {
  public static void createEmailsFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Emails emails = newCustomer.getEmails();
    emails.setCustomerId(newCustomer.getId());
    for (Email email : emails.getEmails()) {
      email.setCustomerId(newCustomer.getId());
      email.validate();
      EmailRepository.create(email);
    }
  }

  public static void updateEmailsFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Emails emails = newCustomer.getEmails();
    emails.setCustomerId(newCustomer.getId());
    for (Email email : emails.getEmails()) {
      email.validate();
      email.setCustomerId(newCustomer.getId());
      EmailRepository.update(email);
    }
  }

  public static Emails getEmails(Customer customer) throws RuntimeException {
    return EmailsRepository.get(customer);
  }
}
