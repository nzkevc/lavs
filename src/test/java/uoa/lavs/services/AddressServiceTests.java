package uoa.lavs.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Address;
import uoa.lavs.models.Customer;
import uoa.lavs.repository.AddressesRepository;
import uoa.lavs.repository.CustomerRepository;
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
    customer = CustomerRepository.create(customer);

    Address address = TestEntityCreator.createBasicAddress(customer);

    customer.getAddresses().addAddress(address);

    // Act
    AddressService.createAddressesFromCustomer(customer);
    customer.setAddresses(AddressesRepository.get(customer));

    // Assert
    assertEquals(customer.getId(), customer.getAddresses().getCustomerId());
    assertEquals(1, customer.getAddresses().getAddresses().size());
    assertEquals(address, customer.getAddresses().getPrimaryAddress());
  }

  @Test
  public void createMultipleAddressesFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Address address = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address address2 = TestEntityCreator.createBasicSecondaryAddress(customer);
    Address address3 = TestEntityCreator.createBasicMailingAddress(customer);

    customer.getAddresses().addAddress(address);
    customer.getAddresses().addAddress(address2);
    customer.getAddresses().addAddress(address3);

    // Act
    AddressService.createAddressesFromCustomer(customer);
    customer.setAddresses(AddressesRepository.get(customer));

    // Assert
    assertEquals(customer.getId(), customer.getAddresses().getCustomerId());
    assertEquals(3, customer.getAddresses().getAddresses().size());
    assertEquals(address3, customer.getAddresses().getMailingAddress());
  }

  @Test
  public void getAddressesFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Address address = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address address2 = TestEntityCreator.createBasicSecondaryAddress(customer);
    Address address3 = TestEntityCreator.createBasicMailingAddress(customer);

    customer.getAddresses().addAddress(address);
    customer.getAddresses().addAddress(address3);

    // Act
    AddressService.createAddressesFromCustomer(customer);
    customer.getAddresses().addAddress(address2);
    customer.setAddresses(AddressService.getAddresses(customer));

    // Assert
    assertEquals(customer.getId(), customer.getAddresses().getCustomerId());
    assertEquals(2, customer.getAddresses().getAddresses().size());
    assertEquals(address3, customer.getAddresses().getMailingAddress());
    assertEquals(address, customer.getAddresses().getPrimaryAddress());
  }

  @Test
  public void updateAddressesFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Address address = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);

    customer.getAddresses().addAddress(address);

    // Act
    AddressService.createAddressesFromCustomer(customer);
    customer.getAddresses().addAddress(address2);
    AddressService.updateAddressesFromCustomer(customer);
    customer.setAddresses(AddressService.getAddresses(customer));

    // Assert
    assertEquals(address2, customer.getAddresses().getPrimaryAddress());
  }

  @Test
  public void updateMultipleAddressesFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Address address = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicSecondaryAddress(customer);
    Address address3 = TestEntityCreator.createBasicMailingAddress(customer);

    customer.getAddresses().addAddress(address);

    // Act
    AddressService.createAddressesFromCustomer(customer);
    customer.getAddresses().addAddress(address2);
    customer.getAddresses().addAddress(address3);
    AddressService.updateAddressesFromCustomer(customer);
    customer.setAddresses(AddressService.getAddresses(customer));

    // Assert
    assertEquals(address3, customer.getAddresses().getMailingAddress());
  }
}
