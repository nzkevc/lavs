package uoa.lavs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Address;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.objects.ConnectionInstance;

public class AddressServiceTests {
  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createAddressesFromCustomerTest() {
    // Arrange
    AddressService addressService = new AddressService();
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address address2 = TestEntityCreator.createBasicSecondaryAddress(customer);
    Address address3 = TestEntityCreator.createBasicMailingAddress(customer);

    customer.getAddresses().addAddress(address);
    customer.getAddresses().addAddress(address2);
    customer.getAddresses().addAddress(address3);

    // Act
    // Assert
  }

  @Test
  public void createMultipleAddressesFromCustomerTest() {
    // Arrange
    // Act
    // Assert
  }

  @Test
  public void getAddressesFromCustomerTest() {
    // Arrange
    // Act
    // Assert
  }

  @Test
  public void updateAddressesFromCustomerTest() {
    // Arrange
    // Act
    // Assert
  }

  @Test
  public void updateMultipleAddressesFromCustomerTest() {
    // Arrange
    // Act
    // Assert
  }
}
