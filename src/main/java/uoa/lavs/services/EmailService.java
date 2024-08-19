package uoa.lavs.services;

import uoa.lavs.exceptions.ValidationException;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Emails;
import uoa.lavs.repository.EmailRepository;
import uoa.lavs.repository.EmailsRepository;

class EmailService {
  public static void createEmailsFromCustomer(Customer newCustomer) {
    // create emails
  }

  public static void updateEmailsFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Emails emails = newCustomer.getEmails();
    for (Email email : emails.getEmails()) {
      email.validate();
      EmailRepository.update(email);
    }
  }

  public static Emails getEmails(Customer customer) throws RuntimeException {
    return EmailsRepository.get(customer);
  }
}
