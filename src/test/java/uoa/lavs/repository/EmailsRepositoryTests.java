package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Emails;
import uoa.lavs.utils.ConnectionInstance;

public class EmailsRepositoryTests {

  @BeforeAll
  public static void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void getEmailsTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Email primaryEmail = TestEntityCreator.createBasicEmail(customer);
    Email otherEmail = TestEntityCreator.createBasicNonPrimaryEmail(customer);

    primaryEmail = EmailRepository.create(primaryEmail);
    otherEmail = EmailRepository.create(otherEmail);

    // Act
    Emails emails = EmailsRepository.get(customer);
    Email fetchedPrimaryEmail = emails.getPrimaryEmail();

    // Assert
    assertEquals(primaryEmail.getAddress(), fetchedPrimaryEmail.getAddress());
    assertEquals(2, emails.getEmailCount());
  }

  @Test
  public void getEmailsNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> EmailsRepository.get(customer));
  }
}
