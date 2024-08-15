package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer;

public class CustomerRepositoryTests {

  private Customer createCustomer() {
    LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
    return new Customer.Builder(
            "1", "Mr", "John Doe", dateOfBirth, "Consultant", "New Zealand", "N/A")
        .build();
  }

  @Test
  public void createCustomerTest() {
    // Arrange
    Customer customer = createCustomer();
    customer = CustomerRepository.create(customer);

    // Act
    String id = customer.getId();

    // Assert
    assertNotEquals(null, id);
  }

  @Test
  public void updateCustomerTest() {
    // Arrange
    Customer customer = createCustomer();
    customer = CustomerRepository.create(customer);

    // Act
    customer.setName("Jane Doe");
    customer = CustomerRepository.update(customer);

    // Assert
    assertEquals("Jane Doe", customer.getName());
  }

  @Test
  public void updateNonExistentCustomerTest() {
    // Arrange
    Customer customer = createCustomer();
    customer.setId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> CustomerRepository.update(customer));
  }

  @Test
  public void getCustomerTest() {
    // Arrange
    Customer customer = createCustomer();
    customer = CustomerRepository.create(customer);

    // Act
    customer.setName("Jane Doe");
    customer = CustomerRepository.get(customer.getId());

    // Assert
    assertEquals("John Doe", customer.getName());
  }

  @Test
  void getNonExistentCustomerTest() {
    // Arrange
    Customer customer = createCustomer();
    customer.setId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> CustomerRepository.get(customer.getId()));
  }
}
