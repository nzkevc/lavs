package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Employer;
import uoa.lavs.utils.objects.ConnectionInstance;

public class EmployerRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createEmployerTest() {
    // Arrange
    EmployerRepository employerRepository = new EmployerRepository();
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Employer employer = TestEntityCreator.createBasicEmployer(customer);

    // Act
    employer = EmployerRepository.create(employer);

    // Assert
    assertNotEquals(null, employer.getNumber());
  }

  @Test
  public void createEmployerNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Employer employer = TestEntityCreator.createBasicEmployer(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> EmployerRepository.create(employer));
  }

  @Test
  public void updateEmployerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Employer employer = TestEntityCreator.createBasicEmployer(customer);

    // Act
    employer.setName("New Name");
    employer = EmployerRepository.update(employer);

    // Assert
    assertEquals("New Name", employer.getName());
  }

  @Test
  public void updateEmployerNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Employer employer = TestEntityCreator.createBasicEmployer(customer);
    employer.setCustomerId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> EmployerRepository.update(employer));
  }

  @Test
  public void getEmployerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Employer employer = TestEntityCreator.createBasicEmployer(customer);
    employer = EmployerRepository.create(employer);

    // Act
    Employer retrievedEmployer = EmployerRepository.get(customer.getId(), employer.getNumber());

    // Assert
    assertEquals(employer.getNumber(), retrievedEmployer.getNumber());
  }

  @Test
  public void getNonExistentEmployerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Employer employer = TestEntityCreator.createBasicEmployer(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> EmployerRepository.get(employer.getCustomerId(), 1));
  }
}
