package uoa.lavs.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.repository.CustomerRepository;
import uoa.lavs.repository.EmailsRepository;
import uoa.lavs.utils.objects.ConnectionInstance;

public class EmailServiceTests {
  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createEmailsFromCustomerTest() {
    // Arrange
    EmailService emailService = new EmailService();
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Email email = TestEntityCreator.createBasicEmail(customer);

    customer.getEmails().addEmail(email);

    // Act
    EmailService.createEmailsFromCustomer(customer);
    customer.setEmails(EmailsRepository.get(customer));

    // Assert
    assertEquals(customer.getId(), customer.getEmails().getCustomerId());
    assertEquals(1, customer.getEmails().getEmails().size());
    assertEquals(email, customer.getEmails().getPrimaryEmail());
  }

  @Test
  public void createMultipleEmailsFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Email email = TestEntityCreator.createBasicNonPrimaryEmail(customer);
    Email email2 = TestEntityCreator.createBasicEmail(customer);

    customer.getEmails().addEmail(email);
    customer.getEmails().addEmail(email2);

    // Act
    EmailService.createEmailsFromCustomer(customer);
    customer.setEmails(EmailsRepository.get(customer));

    // Assert
    assertEquals(customer.getId(), customer.getEmails().getCustomerId());
    assertEquals(2, customer.getEmails().getEmails().size());
    assertEquals(email2, customer.getEmails().getPrimaryEmail());
  }

  @Test
  public void getEmailsFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Email email = TestEntityCreator.createBasicEmail(customer);
    Email email2 = TestEntityCreator.createBasicNonPrimaryEmail(customer);
    Email email3 = TestEntityCreator.createBasicNonPrimaryEmail(customer);
    email3.setIsPrimary(true);
    email3.setAddress("different@address.com");

    customer.getEmails().addEmail(email);
    customer.getEmails().addEmail(email2);

    // Act
    EmailService.createEmailsFromCustomer(customer);
    customer.getEmails().addEmail(email3);
    customer.setEmails(EmailService.getEmails(customer));

    // Assert
    assertEquals(customer.getId(), customer.getEmails().getCustomerId());
    assertEquals(2, customer.getEmails().getEmails().size());
    assertEquals(email, customer.getEmails().getPrimaryEmail());
    assertNotEquals(email3.getAddress(), customer.getEmails().getPrimaryEmail().getAddress());
  }

  @Test
  public void updateEmailsFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Email email = TestEntityCreator.createBasicEmail(customer);
    Email email2 = TestEntityCreator.createBasicNonPrimaryEmail(customer);
    email2.setIsPrimary(true);

    customer.getEmails().addEmail(email);

    // Act
    EmailService.createEmailsFromCustomer(customer);
    customer.getEmails().addEmail(email2);
    EmailService.updateEmailsFromCustomer(customer);
    customer.setEmails(EmailsRepository.get(customer));

    // Assert
    assertEquals(email2, customer.getEmails().getPrimaryEmail());
  }

  @Test
  public void updateMultipleEmailsFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Email email = TestEntityCreator.createBasicEmail(customer);
    Email email2 = TestEntityCreator.createBasicNonPrimaryEmail(customer);
    Email email3 = TestEntityCreator.createBasicNonPrimaryEmail(customer);
    email3.setIsPrimary(true);

    customer.getEmails().addEmail(email);

    // Act
    EmailService.createEmailsFromCustomer(customer);
    customer.getEmails().addEmail(email2);
    customer.getEmails().addEmail(email3);
    EmailService.updateEmailsFromCustomer(customer);
    customer.setEmails(EmailsRepository.get(customer));

    // Assert
    assertEquals(email3, customer.getEmails().getPrimaryEmail());
    assertEquals(3, customer.getEmails().getEmails().size());
  }
}
