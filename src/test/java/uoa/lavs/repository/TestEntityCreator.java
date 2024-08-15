package uoa.lavs.repository;

import java.time.LocalDate;
import uoa.lavs.models.Address;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Phone;

public class TestEntityCreator {
  public static Customer createBasicCustomer() {
    LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
    return new Customer.Builder(
            "1", "Mr", "John Doe", dateOfBirth, "Consultant", "New Zealand", "N/A")
        .build();
  }

  public static Address createBasicAddress(Customer customer) {
    return new Address(
        customer.getId(),
        null,
        "Home",
        "123 Fake St",
        null,
        "Fakeville",
        "Faketown",
        "1234",
        "New Zealand",
        true,
        true);
  }

  public static Email createBasicEmail(Customer customer) {
    return new Email(customer.getId(), null, "fakeEmail@fake.com", true);
  }

  public static Phone createBasicPhone(Customer customer) {
    return new Phone(customer.getId(), null, "Mobile", "021", "1234567", true, true);
  }
}
