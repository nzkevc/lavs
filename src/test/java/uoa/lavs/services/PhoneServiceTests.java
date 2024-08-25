package uoa.lavs.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Phone;
import uoa.lavs.repository.CustomerRepository;
import uoa.lavs.repository.PhonesRepository;
import uoa.lavs.utils.objects.ConnectionInstance;

public class PhoneServiceTests {
  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createPhonesFromCustomerTest() {
    // Arrange
    PhoneService phoneService = new PhoneService();
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Phone phone = TestEntityCreator.createBasicPhone(customer);

    customer.getPhones().addPhone(phone);

    // Act
    PhoneService.createPhonesFromCustomer(customer);
    customer.setPhones(PhonesRepository.get(customer));

    // Assert
    assertEquals(customer.getId(), customer.getAddresses().getCustomerId());
    assertEquals(1, customer.getPhones().getPhoneCount());
    assertEquals(phone, customer.getPhones().getPrimaryPhone());
  }

  @Test
  public void createMultiplePhonesFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Phone phone = TestEntityCreator.createBasicPrimaryPhone(customer);
    Phone phone2 = TestEntityCreator.createBasicTextPhone(customer);
    Phone phone3 = TestEntityCreator.createBasicSecondaryPhone(customer);

    customer.getPhones().addPhone(phone);
    customer.getPhones().addPhone(phone2);
    customer.getPhones().addPhone(phone3);

    // Act
    PhoneService.createPhonesFromCustomer(customer);
    customer.setPhones(PhonesRepository.get(customer));

    // Assert
    assertEquals(customer.getId(), customer.getAddresses().getCustomerId());
    assertEquals(3, customer.getPhones().getPhoneCount());
    assertEquals(phone2, customer.getPhones().getTextPhone());
  }

  @Test
  public void getPhonesFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Phone phone = TestEntityCreator.createBasicPrimaryPhone(customer);
    Phone phone2 = TestEntityCreator.createBasicSecondaryPhone(customer);
    Phone phone3 = TestEntityCreator.createBasicTextPhone(customer);

    customer.getPhones().addPhone(phone);
    customer.getPhones().addPhone(phone3);

    // Act
    PhoneService.createPhonesFromCustomer(customer);
    customer.getPhones().addPhone(phone2);
    customer.setPhones(PhoneService.getPhones(customer));

    // Assert
    assertEquals(customer.getId(), customer.getPhones().getCustomerId());
    assertEquals(2, customer.getPhones().getPhoneCount());
    assertEquals(phone3, customer.getPhones().getTextPhone());
    assertEquals(phone, customer.getPhones().getPrimaryPhone());
  }

  @Test
  public void updatePhonesFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Phone phone = TestEntityCreator.createBasicPrimaryPhone(customer);
    Phone phone2 = TestEntityCreator.createBasicPhone(customer);

    customer.getPhones().addPhone(phone);

    // Act
    PhoneService.createPhonesFromCustomer(customer);
    customer.getPhones().addPhone(phone2);
    PhoneService.updatePhonesFromCustomer(customer);
    customer.setPhones(PhoneService.getPhones(customer));

    // Assert
    assertEquals(phone2, customer.getPhones().getPrimaryPhone());
  }

  @Test
  public void updateMultiplePhonesFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Phone phone = TestEntityCreator.createBasicPhone(customer);
    Phone phone2 = TestEntityCreator.createBasicSecondaryPhone(customer);
    Phone phone3 = TestEntityCreator.createBasicTextPhone(customer);

    customer.getPhones().addPhone(phone);

    // Act
    PhoneService.createPhonesFromCustomer(customer);
    customer.getPhones().addPhone(phone2);
    customer.getPhones().addPhone(phone3);
    PhoneService.updatePhonesFromCustomer(customer);
    customer.setPhones(PhoneService.getPhones(customer));

    // Assert
    assertEquals(phone3, customer.getPhones().getTextPhone());
  }
}
