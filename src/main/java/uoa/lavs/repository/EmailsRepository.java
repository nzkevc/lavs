package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmails;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Emails;
import uoa.lavs.utils.objects.ConnectionInstance;

public class EmailsRepository {
  /** Retrieves all the emails registered to a customer in an Emails object */
  public static Emails get(Customer customer) {
    Connection connection = ConnectionInstance.getConnection();

    LoadCustomerEmails message = new LoadCustomerEmails();
    message.setCustomerId(customer.getId());

    Status status = message.send(connection);
    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get emails: " + status.getErrorMessage());
    }

    Emails emails = new Emails(customer.getId());
    int count = message.getCountFromServer();

    for (int i = 1; i <= count; i++) {
      Email email = EmailRepository.get(customer.getId(), message.getNumberFromServer(i));
      emails.addEmail(email);
    }

    return emails;
  }
}
