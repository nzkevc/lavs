package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Employers;
import uoa.lavs.utils.objects.ConnectionInstance;

public class EmployersRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void getEmployersTest() {
    // Arrange
    EmployersRepository employersRepository = new EmployersRepository();

    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Employer employer1 = TestEntityCreator.createBasicEmployer(customer);
    Employer employer2 = TestEntityCreator.createBasicSecondaryEmployer(customer);

    employer1 = EmployerRepository.create(employer1);
    employer2 = EmployerRepository.create(employer2);

    // Act
    Employers employers = EmployersRepository.get(customer);

    // Assert
    assertEquals(2, employers.getEmployerCount());
  }

  @Test
  public void getEmployersNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> EmployersRepository.get(customer));
  }

  @Test
  public void getNoEmployersTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    // Act
    Employers employers = EmployersRepository.get(customer);

    // Assert
    assertEquals(0, employers.getEmployerCount());
  }
}
