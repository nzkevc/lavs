package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Address;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.objects.ConnectionInstance;

public class AddressesRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void getAddressesTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);
    Address otherAddress = TestEntityCreator.createBasicSecondaryAddress(customer);

    primaryAddress = AddressRepository.create(primaryAddress);
    mailingAddress = AddressRepository.create(mailingAddress);
    otherAddress = AddressRepository.create(otherAddress);

    // Act
    Addresses addresses = AddressesRepository.get(customer);
    Address fetchedPrimaryAddress = addresses.getResidentialAddress();
    Address fetchedMailingAddress = addresses.getMailingAddress();

    // Assert
    assertEquals(primaryAddress.getNumber(), fetchedPrimaryAddress.getNumber());
    assertEquals(mailingAddress.getNumber(), fetchedMailingAddress.getNumber());
    assertNotEquals(fetchedMailingAddress.getNumber(), fetchedPrimaryAddress.getNumber());
    assertEquals(3, addresses.getAddressCount());
  }

  @Test
  public void getAddressesNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> AddressesRepository.get(customer));
  }
}
