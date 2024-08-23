package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmployers;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Employers;
import uoa.lavs.utils.objects.ConnectionInstance;

public class EmployersRepository {
  /** Retrieves all the employers registers to a customer in an Employers object */
  public static Employers get(Customer customer) {
    Connection connection = ConnectionInstance.getConnection();

    LoadCustomerEmployers message = new LoadCustomerEmployers();
    message.setCustomerId(customer.getId());

    Status status = message.send(connection);
    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get employers: " + status.getErrorMessage());
    }

    Employers employers = new Employers(customer.getId());
    int count = message.getCountFromServer();

    for (int i = 1; i <= count; i++) {
      Employer employer = EmployerRepository.get(customer.getId(), message.getNumberFromServer(i));

      employers.addEmployer(employer);
    }

    return employers;
  }
}
