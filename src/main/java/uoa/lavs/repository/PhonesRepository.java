package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerPhoneNumbers;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Phone;
import uoa.lavs.models.Phones;
import uoa.lavs.utils.objects.ConnectionInstance;

public class PhonesRepository {
  /** Retrieves all the phoneNumbers registered to a customer in a Phones object */
  public static Phones get(Customer customer) {
    Connection connection = ConnectionInstance.getConnection();

    LoadCustomerPhoneNumbers message = new LoadCustomerPhoneNumbers();
    message.setCustomerId(customer.getId());

    Status status = message.send(connection);
    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get phone numbers: " + status.getErrorMessage());
    }

    Phones phones = new Phones(customer.getId());
    int count = message.getCountFromServer();

    for (int i = 1; i <= count; i++) {
      Phone phone = PhoneRepository.get(customer.getId(), message.getNumberFromServer(i));
      phones.addPhone(phone);
    }

    return phones;
  }
}
