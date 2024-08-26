package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.utils.objects.ValidationException;

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
  public void addressesConstructorTwoTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);

    Set<Address> addressSet = new HashSet<>();
    addressSet.add(primaryAddress);
    addressSet.add(mailingAddress);

    Addresses addresses = new Addresses(addressSet);

    // Act

    // Assert
    assertEquals(primaryAddress, addresses.getPrimaryAddress());
    assertEquals(mailingAddress, addresses.getMailingAddress());
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

  @Test
  public void validatePrimaryAndMailingAddress() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(primaryAddress);
    addresses.addAddress(mailingAddress);

    // Act
    // Assert
    assertDoesNotThrow(() -> Addresses.validate(addresses));
  }

  @Test
  public void validateTwoPrimaryAddresses() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address newPrimaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);
    newPrimaryAddress.setType("Work");

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(primaryAddress);
    addresses.addAddress(mailingAddress);
    addresses.setPrimaryAddress(newPrimaryAddress);

    // Act
    // Assert
    assertDoesNotThrow(() -> Addresses.validate(addresses));
  }

  @Test
  public void validateTwoMailingAddresses() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);
    Address newMailingAddress = TestEntityCreator.createBasicMailingAddress(customer);
    newMailingAddress.setType("Work");

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(primaryAddress);
    addresses.addAddress(mailingAddress);
    addresses.setMailingAddress(newMailingAddress);

    // Act
    // Assert
    assertDoesNotThrow(() -> Addresses.validate(addresses));
  }

  @Test
  public void validateNoPrimaryAddress() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address mailingAddress = TestEntityCreator.createBasicMailingAddress(customer);

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(mailingAddress);

    // Act
    // Assert
    assertThrows(ValidationException.class, () -> Addresses.validate(addresses));
  }

  @Test
  public void validateNoMailingAddress() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address primaryAddress = TestEntityCreator.createBasicPrimaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(primaryAddress);

    // Act
    // Assert
    assertThrows(ValidationException.class, () -> Addresses.validate(addresses));
  }

  @Test
  public void validateNoAddresses() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();

    Addresses addresses = new Addresses(customer.getId());

    // Act
    // Assert
    assertDoesNotThrow(() -> Addresses.validate(addresses));
  }

  @Test
  public void validateNoPrimaryOrMailingAddress() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address secondaryAddress = TestEntityCreator.createBasicSecondaryAddress(customer);

    Addresses addresses = new Addresses(customer.getId());
    addresses.addAddress(secondaryAddress);

    // Act
    // Assert
    assertThrows(ValidationException.class, () -> Addresses.validate(addresses));
  }
}
