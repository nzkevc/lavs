package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Address;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.objects.ConnectionInstance;

public class AddressRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createAddressTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Address address = TestEntityCreator.createBasicAddress(customer);

    // Act
    address = AddressRepository.create(address);

    // Assert
    assertNotEquals(null, address.getNumber());
  }

  @Test
  public void createAddressNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Address address = TestEntityCreator.createBasicAddress(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> AddressRepository.create(address));
  }

  @Test
  public void updateAddressTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Address address = TestEntityCreator.createBasicAddress(customer);

    // Act
    address.setLine1("456 Real St");
    address = AddressRepository.update(address);

    // Assert
    assertEquals("456 Real St", address.getLine1());
  }

  @Test
  public void updateNonExistentAddressTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Address address = TestEntityCreator.createBasicAddress(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> AddressRepository.update(address));
  }

  @Test
  public void getAddressTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Address address = TestEntityCreator.createBasicAddress(customer);
    address = AddressRepository.create(address);

    // Act
    address.setLine1("456 Real St");
    address = AddressRepository.get(address.getCustomerId(), address.getNumber());

    // Assert
    assertEquals("123 Fake St", address.getLine1());
  }

  @Test
  public void getNonExistentAddressTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Address address = TestEntityCreator.createBasicAddress(customer);

    // Act

    // Assert
    assertThrows(
        RuntimeException.class,
        () -> AddressRepository.get(address.getCustomerId(), address.getNumber()));
  }
}
