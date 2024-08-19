package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Phone;
import uoa.lavs.models.Phones;
import uoa.lavs.utils.ConnectionInstance;

public class PhonesRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void getPhonesTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Phone primaryPhone = TestEntityCreator.createBasicPrimaryPhone(customer);
    Phone textPhone = TestEntityCreator.createBasicTextPhone(customer);
    Phone otherPhone = TestEntityCreator.createBasicSecondaryPhone(customer);

    primaryPhone = PhoneRepository.create(primaryPhone);
    textPhone = PhoneRepository.create(textPhone);
    otherPhone = PhoneRepository.create(otherPhone);

    // Act
    Phones phones = PhonesRepository.get(customer);
    Phone fetchedPrimaryPhone = phones.getPrimaryPhone();
    Phone fetchedTextPhone = phones.getTextPhone();

    // Assert
    assertEquals(primaryPhone.getNumber(), fetchedPrimaryPhone.getNumber());
    assertEquals(textPhone.getNumber(), fetchedTextPhone.getNumber());
    assertNotEquals(fetchedTextPhone.getNumber(), fetchedPrimaryPhone.getNumber());
    assertEquals(3, phones.getPhoneCount());
  }

  @Test
  public void getPhonesNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> PhonesRepository.get(customer));
  }
}
