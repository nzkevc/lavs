package uoa.lavs.repository;

import java.time.LocalDate;
import uoa.lavs.models.Customer;

public abstract class RepositoryTest {
  protected Customer createBasicCustomer() {
    LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
    return new Customer.Builder(
            "1", "Mr", "John Doe", dateOfBirth, "Consultant", "New Zealand", "N/A")
        .build();
  }
}
