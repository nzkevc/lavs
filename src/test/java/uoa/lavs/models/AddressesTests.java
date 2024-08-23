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
    addresses1.setResidentialAddress(primaryAddress);
    addresses1.setMailingAddress(mailingAddress);

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
    addresses2.setMailingAddress(mailingAddress);

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
    assertEquals(secondaryAddress, addresses.getAddresses().get(0));
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
}
