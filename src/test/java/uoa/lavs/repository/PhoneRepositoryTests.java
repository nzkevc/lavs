package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ConnectionInstance;

public class PhoneRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createPhoneTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Phone phone = TestEntityCreator.createBasicPhone(customer);

    // Act
    phone = PhoneRepository.create(phone);

    // Assert
    assertNotEquals(null, phone.getNumber());
  }

  @Test
  public void createPhoneNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Phone phone = TestEntityCreator.createBasicPhone(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> PhoneRepository.create(phone));
  }

  @Test
  public void updatePhoneTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Phone phone = TestEntityCreator.createBasicPhone(customer);

    // Act
    phone.setPhoneNumber("0211234567");
    phone = PhoneRepository.update(phone);

    // Assert
    assertEquals("0211234567", phone.getPhoneNumber());
  }

  @Test
  public void updateNonExistentPhoneTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Phone phone = TestEntityCreator.createBasicPhone(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> PhoneRepository.update(phone));
  }

  @Test
  public void getPhoneTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Phone phone = TestEntityCreator.createBasicPhone(customer);
    phone = PhoneRepository.create(phone);

    // Act
    phone.setPhoneNumber("7654321");
    phone = PhoneRepository.get(phone.getCustomerId(), phone.getNumber());

    // Assert
    assertEquals("1234567", phone.getPhoneNumber());
  }

  @Test
  public void getNonExistentPhoneTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Phone phone = TestEntityCreator.createBasicPhone(customer);

    // Act

    // Assert
    assertThrows(
        RuntimeException.class,
        () -> PhoneRepository.get(phone.getCustomerId(), phone.getNumber()));
  }
}
