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
    assertEquals(primaryAddress, addresses1.getResidentialAddress());
    assertEquals(mailingAddress, addresses1.getMailingAddress());
  }

  @Test
  public void addressesContructor2Test() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);

    Addresses addresses2 = new Addresses(customer.getId(), primaryAddress);

    // Act
    addresses2.addAddress(mailingAddress);

    // Assert
    assertEquals(primaryAddress, addresses2.getResidentialAddress());
    assertEquals(mailingAddress, addresses2.getMailingAddress());
  }

  @Test
  public void addressesConstructor3Test() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);

    Addresses addresses3 = new Addresses(customer.getId(), primaryAddress, mailingAddress);

    // Act

    // Assert
    assertEquals(primaryAddress, addresses3.getResidentialAddress());
    assertEquals(mailingAddress, addresses3.getMailingAddress());
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

    Addresses addresses = new Addresses(customer.getId(), primaryAddress, mailingAddress);

    // Act
    addresses.setResidentialAddress(newPrimaryAddress);

    // Assert
    assertEquals(newPrimaryAddress, addresses.getResidentialAddress());
    assertFalse(primaryAddress.getPrimary());
  }

  @Test
  public void addressesAddNullAddressTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId(), primaryAddress);

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

    Addresses addresses = new Addresses(customer.getId(), primaryAddress, mailingAddress);

    // Act
    addresses.addAddress(null);

    // Assert
    assertEquals(primaryAddress, addresses.getResidentialAddress());
  }

  @Test
  public void addingNullMailingAddress() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId(), primaryAddress, mailingAddress);

    // Act
    addresses.addAddress(null);

    // Assert
    assertEquals(mailingAddress, addresses.getMailingAddress());
  }
}
