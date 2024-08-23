package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
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
    CustomerRepository customerRepository = new CustomerRepository();
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

  @Test
  public void getCustomersByNameTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);
    Customer customerTwo = TestEntityCreator.createBasicCustomer();
    customerTwo = CustomerRepository.create(customerTwo);

    // Act
    List<Customer> customers = CustomerRepository.getCustomersByName("John Doe");

    // Assert
    assertEquals(2, customers.size());
  }

  @Test
  public void getCustomersByNonExistentNameTest() {
    // Arrange

    // Act
    List<Customer> customers = CustomerRepository.getCustomersByName("Non Existent");

    // Assert
    assertEquals(0, customers.size());
  }
}
