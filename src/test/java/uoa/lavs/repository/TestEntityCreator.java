package uoa.lavs.repository;

import java.time.LocalDate;
import uoa.lavs.models.Address;
import uoa.lavs.models.Customer;

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
}
