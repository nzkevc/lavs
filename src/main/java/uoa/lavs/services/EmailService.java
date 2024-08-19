package uoa.lavs.services;

import uoa.lavs.models.Customer;
import uoa.lavs.models.Emails;
import uoa.lavs.repository.EmailsRepository;

class EmailService {
  static void createEmailsFromCustomer(Customer newCustomer) {
    // create emails
  }

  static void updateEmailsFromCustomer(Customer newCustomer) {
    // update emails
  }

  static Emails getEmails(Customer customer) throws RuntimeException {
    return EmailsRepository.get(customer);
  }
}
