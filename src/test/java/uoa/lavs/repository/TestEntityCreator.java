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

  public static Address createBasicPrimaryAddress(Customer customer) {
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
        false);
  }

  public static Address createBasicMailingAddress(Customer customer) {
    return new Address(
        customer.getId(),
        null,
        "Home",
        "123 Faker Ln",
        null,
        "Fakerton",
        "Fakecity",
        "1234",
        "New Zealand",
        false,
        true);
  }

  public static Address createBasicSecondaryAddress(Customer customer) {
    return new Address(
        customer.getId(),
        null,
        "Home",
        "321 Fakes Rd",
        null,
        "Fakecity",
        "Fakecity",
        "2312",
        "New Zealand",
        false,
        false);
  }

  public static Email createBasicEmail(Customer customer) {
    return new Email(customer.getId(), null, "fakeEmail@fake.com", true);
  }

  public static Email createBasicNonPrimaryEmail(Customer customer) {
    return new Email(customer.getId(), null, "fakeEmail2@fake.com", false);
  }

  public static Phone createBasicPhone(Customer customer) {
    return new Phone(customer.getId(), null, "Mobile", "021", "1234567", true, true);
  }

  public static Phone createBasicPrimaryPhone(Customer customer) {
    return new Phone(customer.getId(), null, "Home", "09", "1234567", true, false);
  }

  public static Phone createBasicTextPhone(Customer customer) {
    return new Phone(customer.getId(), null, "Mobile", "022", "7654321", false, true);
  }

  public static Phone createBasicSecondaryPhone(Customer customer) {
    return new Phone(customer.getId(), null, "Work", "09", "123987", false, false);
  }
}
