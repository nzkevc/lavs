package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;

public class EmailRepositoryTests {
  @Test
  public void createEmailTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Email email = TestEntityCreator.createBasicEmail(customer);

    // Act
    email = EmailRepository.create(email);

    // Assert
    assertNotEquals(null, email.getNumber());
  }

  @Test
  public void createEmailNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Email email = TestEntityCreator.createBasicEmail(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> EmailRepository.create(email));
  }

  @Test
  public void updateEmailTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Email email = TestEntityCreator.createBasicEmail(customer);

    // Act
    email.setAddress("notFake@fake.com");
    email = EmailRepository.update(email);

    // Assert
    assertEquals("notFake@fake.com", email.getAddress());
  }

  @Test
  public void updateNonExistentEmailTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Email email = TestEntityCreator.createBasicEmail(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> EmailRepository.update(email));
  }

  @Test
  public void getEmailTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Email email = TestEntityCreator.createBasicEmail(customer);
    email = EmailRepository.create(email);

    // Act
    email.setAddress("notFake@fake.com");
    email = EmailRepository.get(email.getCustomerId(), email.getNumber());

    // Assert
    assertEquals("fakeEmail@fake.com", email.getAddress());
  }

  @Test
  public void getEmailNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Email email = TestEntityCreator.createBasicEmail(customer);

    // Act

    // Assert
    assertThrows(
        RuntimeException.class,
        () -> EmailRepository.get(email.getCustomerId(), email.getNumber()));
  }
}
