package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uoa.lavs.models.Customer;
import uoa.lavs.utils.objects.ConnectionInstance;

public class CustomerRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    // Act
    String id = customer.getId();

    // Assert
    assertNotEquals(null, id);
  }

  @Test
  public void updateCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
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
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> CustomerRepository.update(customer));
  }

  @Test
  public void getCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
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
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> CustomerRepository.get(customer.getId()));
  }
}
