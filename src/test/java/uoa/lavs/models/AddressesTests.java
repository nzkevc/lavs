package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;

public class AddressesTests {
  @Test
  public void addressesConstructorTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);

    Addresses addresses1 = new Addresses(customer.getId());

    // Act
    addresses1.addAddress(primaryAddress);
    addresses1.addAddress(mailingAddress);

    // Assert
    assertEquals(primaryAddress, addresses1.getPrimaryAddress());
    assertEquals(mailingAddress, addresses1.getMailingAddress());
  }

  @Test
  public void addressesAddNonKeyAddressTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address secondaryAddress = TestEntityCreator.createBasicSecondaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId());

    // Act
    addresses.addAddress(secondaryAddress);

    // Assert
    assertEquals(1, addresses.getAddressCount());
  }

  @Test
  public void addressesCustomerIDTest() {
    // Arrange
    String customerId = "10000";
    Addresses addresses = new Addresses(customerId);
    addresses.setCustomerId("10001");

    // Act

    // Assert
    assertEquals("10001", addresses.getCustomerId());
  }

  @Test
  public void addingNewResidentialAddress() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address newPrimaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(mailingAddress);
    addresses.addAddress(primaryAddress);

    // Act
    addresses.setPrimaryAddress(newPrimaryAddress);

    // Assert
    assertEquals(newPrimaryAddress, addresses.getPrimaryAddress());
    assertFalse(primaryAddress.getPrimary());
  }

  @Test
  public void addressesAddNullAddressTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(primaryAddress);

    // Act
    addresses.addAddress(null);

    // Assert
    assertEquals(1, addresses.getAddressCount());
  }

  @Test
  public void addingNullResidentialAddressTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(primaryAddress);
    addresses.addAddress(mailingAddress);

    // Act
    addresses.addAddress(null);

    // Assert
    assertEquals(primaryAddress, addresses.getPrimaryAddress());
  }

  @Test
  public void addingNullMailingAddress() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(primaryAddress);
    addresses.addAddress(mailingAddress);

    // Act
    addresses.addAddress(null);

    // Assert
    assertEquals(mailingAddress, addresses.getMailingAddress());
  }
}
