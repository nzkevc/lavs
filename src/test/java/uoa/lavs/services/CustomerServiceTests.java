package uoa.lavs.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.objects.ConnectionInstance;

public class CustomerServiceTests {
  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createCustomerTest() {
    // Arrange
    CustomerService customerService = new CustomerService();
    Customer customer = TestEntityCreator.createBasicCustomer();

    // Act
    CustomerService.createCustomer(customer);

    // Assert
    assertNotNull(customer.getId());
  }

  @Test
  public void createMultipleCustomersTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Customer customer2 = TestEntityCreator.createBasicCustomer();

    // Act
    CustomerService.createCustomer(customer);
    CustomerService.createCustomer(customer2);

    // Assert
    assertNotNull(customer.getId());
    assertNotNull(customer2.getId());
  }

  @Test
  public void createCustomerWithEmployerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = TestEntityCreator.createBasicCustomer();
    customer.getEmployers().addEmployer(TestEntityCreator.createBasicEmployer(customer));

    // Act
    CustomerService.createCustomer(customer);

    // Assert
    assertNotNull(customer.getId());
    assertNotNull(customer.getEmployers().getCustomerId());
    customer
        .getEmployers()
        .getEmployers()
        .forEach(employer -> assertNotNull(employer.getCustomerId()));
    assertEquals(customer.getId(), customer.getEmployers().getCustomerId());
  }

  @Test
  public void getCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    CustomerService.createCustomer(customer);

    // Act
    Customer retrievedCustomer = CustomerService.getCustomer(customer.getId());

    // Assert
    assertEquals(customer.getId(), retrievedCustomer.getId());
  }

  @Test
  public void getCustomerWithNoEmployerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    CustomerService.createCustomer(customer);

    // Act
    Customer retrievedCustomer = CustomerService.getCustomer(customer.getId());

    // Assert
    assertTrue(retrievedCustomer.getEmployers().getEmployers().isEmpty());
  }

  @Test
  public void getCustomerWithEmployerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.getEmployers().addEmployer(TestEntityCreator.createBasicEmployer(customer));
    CustomerService.createCustomer(customer);

    // Act
    Customer retrievedCustomer = CustomerService.getCustomer(customer.getId());

    // Assert
    assertNotNull(retrievedCustomer.getEmployers());
    assertTrue(retrievedCustomer.getEmployers().getEmployers().size() > 0);
    assertEquals(
        customer.getEmployers().getCustomerId(), retrievedCustomer.getEmployers().getCustomerId());
    customer
        .getEmployers()
        .getEmployers()
        .forEach(
            employer ->
                assertEquals(customer.getEmployers().getCustomerId(), employer.getCustomerId()));
  }

  @Test
  public void updateCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    CustomerService.createCustomer(customer);

    // Act
    customer.setName("John");
    CustomerService.updateCustomer(customer);

    // Assert
    assertEquals("John", customer.getName());
  }

  @Test
  public void updateMultipleCustomersTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Customer customer2 = TestEntityCreator.createBasicCustomer();
    customer.setName("Jane Doe");

    CustomerService.createCustomer(customer);
    CustomerService.createCustomer(customer2);

    // Act
    customer.setName("John");
    customer2.setName("Jane");
    CustomerService.updateCustomer(customer);
    CustomerService.updateCustomer(customer2);

    // Assert
    assertEquals("John", customer.getName());
    assertEquals("Jane", customer2.getName());
  }

  @Test
  public void updateCustomerWithEmployerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.getEmployers().addEmployer(TestEntityCreator.createBasicEmployer(customer));
    CustomerService.createCustomer(customer);

    // Act
    customer
        .getEmployers()
        .getEmployers()
        .forEach(employer -> employer.setName("New Employer Name"));
    CustomerService.updateCustomer(customer);

    // Assert
    customer
        .getEmployers()
        .getEmployers()
        .forEach(employer -> assertEquals("New Employer Name", employer.getName()));
  }

  @Test
  public void getCustomerListByNameTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Customer customer2 = TestEntityCreator.createBasicCustomer();
    CustomerService.createCustomer(customer);
    CustomerService.createCustomer(customer2);

    // Act
    List<Customer> customers = CustomerService.getCustomerListByName("John");

    // Assert
    assertEquals(2, customers.size());
    assertTrue(customers.get(0).getName().contains("John"));
    assertTrue(customers.get(1).getName().contains("John"));
  }
}
