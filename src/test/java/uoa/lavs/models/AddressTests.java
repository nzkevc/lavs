package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;

public class AddressTests {
  @Test
  public void addressDataFieldsTest() {
    // Arrange
    Address address = new Address();
    address.setCustomerId("1234");
    address.setNumber(1);
    address.setType("Home");
    address.setLine1("1234 Main St");
    address.setLine2("Apt 101");
    address.setSuburb("Springfield");
    address.setCity("Springfield");
    address.setPostCode("62701");
    address.setCountry("USA");
    address.setIsMailing(false);
    address.setIsPrimary(true);

    // Act
    boolean result =
        address.getCustomerId().equals("1234")
            && address.getNumber() == 1
            && address.getType().equals("Home")
            && address.getLine1().equals("1234 Main St")
            && address.getLine2().equals("Apt 101")
            && address.getSuburb().equals("Springfield")
            && address.getCity().equals("Springfield")
            && address.getPostCode().equals("62701")
            && address.getCountry().equals("USA")
            && !address.getMailing()
            && address.getPrimary();

    // Assert
    assertTrue(result);
  }

  @Test
  public void addressEqualsTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);

    // Act

    // Assert
    assertTrue(address1.equals(address2));
    assertTrue(address2.equals(address1));
  }

  @Test
  public void addressEqualsEmptyTest() {
    // Arrange
    Address address1 = new Address();
    Address address2 = new Address();

    // Act

    // Assert
    assertTrue(address1.equals(address2));
    assertTrue(address2.equals(address1));
  }

  @Test
  public void addressEqualsNullTest() {
    // Arrange
    Address address1 = new Address();
    Address address2 = null;

    // Act

    // Assert
    assertFalse(address1.equals(address2));
  }

  @Test
  public void addressNotEqualsTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = new Address("1234", 1);

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsTypeTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setType("Work");

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsLine1Test() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setLine1("1234 Main St");

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsLine1NullTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = new Address(customer.getId(), 1);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setLine1(null);

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsLine2Test() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setLine2("Apt 101");

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsLine2NullTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = new Address(customer.getId(), 1);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setLine2(null);

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsSuburbTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setSuburb("Springfield");

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsSuburbNullTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = new Address(customer.getId(), 1);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setSuburb(null);

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsCityTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setCity("Springfield");

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsCityNullTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = new Address(customer.getId(), 1);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setCity(null);

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsPostcodeTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setPostCode("62701");

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsPostcodeNullTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = new Address(customer.getId(), 1);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setPostCode(null);

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsCountryTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setCountry("USA");

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsCountryNullTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = new Address(customer.getId(), 1);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setCountry(null);

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsIsPrimaryTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setIsPrimary(false);

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsIsMailingTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    Address address2 = TestEntityCreator.createBasicAddress(customer);
    address2.setIsMailing(false);

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void addressNotEqualsObjectTypeTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    Address address1 = TestEntityCreator.createBasicAddress(customer);
    String address2 = "1234 Main St";

    // Act

    // Assert
    assertFalse(address1.equals(address2));
    assertFalse(address2.equals(address1));
  }

  @Test
  public void validateAddressTest() {
    // Arrange
    Address address = TestEntityCreator.createBasicAddress(TestEntityCreator.createBasicCustomer());

    // Act

    // Assert
    assertTrue(address.validate());
  }
}
