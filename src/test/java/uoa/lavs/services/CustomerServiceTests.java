package uoa.lavs.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Address;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Emails;
import uoa.lavs.models.Loan;
import uoa.lavs.models.Phone;
import uoa.lavs.models.Phones;
import uoa.lavs.utils.objects.ConnectionInstance;

public class CustomerServiceTests {
  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setAddresses(new Addresses(null));
    customer.setEmails(new Emails(null));
    customer.setPhones(new Phones(null));

    Address address = TestEntityCreator.createBasicAddress(customer);
    Email email = TestEntityCreator.createBasicEmail(customer);
    Phone phone = TestEntityCreator.createBasicPhone(customer);

    customer.getAddresses().addAddress(address);
    customer.getAddresses().addAddress(address);

    customer.getEmails().addEmail(email);

    customer.getPhones().addPhone(phone);
    customer.getPhones().addPhone(phone);

    // Act
    CustomerService.createCustomer(customer);

    // Assert
    assertEquals(customer.getId(), customer.getAddresses().getCustomerId());
    assertEquals(customer.getId(), customer.getEmails().getPrimaryEmail().getCustomerId());
    assertEquals(2, customer.getPhones().getPhoneNumbers().size());
  }

  @Test
  public void getCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setAddresses(new Addresses(null));
    customer.setEmails(new Emails(null));
    customer.setPhones(new Phones(null));

    Address address = TestEntityCreator.createBasicAddress(customer);
    Email email = TestEntityCreator.createBasicEmail(customer);
    Phone phone = TestEntityCreator.createBasicPhone(customer);

    customer.getAddresses().addAddress(address);
    customer.getAddresses().addAddress(address);

    customer.getEmails().addEmail(email);

    customer.getPhones().addPhone(phone);
    customer.getPhones().addPhone(phone);

    CustomerService.createCustomer(customer);

    // Act
    Customer retrievedCustomer = CustomerService.getCustomer(customer.getId());

    // Assert
    assertEquals(customer.getId(), retrievedCustomer.getId());
    assertEquals(
        customer.getAddresses().getCustomerId(), retrievedCustomer.getAddresses().getCustomerId());
    assertEquals(
        customer.getEmails().getPrimaryEmail().getCustomerId(),
        retrievedCustomer.getEmails().getPrimaryEmail().getCustomerId());
    assertEquals(
        customer.getPhones().getPrimaryPhone().getNumber(),
        retrievedCustomer.getPhones().getPrimaryPhone().getNumber());
  }

  @Test
  public void updateCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setAddresses(new Addresses(null));
    customer.setEmails(new Emails(null));
    customer.setPhones(new Phones(null));

    // Can add more here as appropriate
    Address address = TestEntityCreator.createBasicAddress(customer);
    Email email = TestEntityCreator.createBasicEmail(customer);
    Phone phone = TestEntityCreator.createBasicPhone(customer);

    // Can be different addresses, emails, phones, but MUST set the key ones here
    customer.getAddresses().addAddress(address);
    customer.getAddresses().addAddress(address);

    customer.getEmails().addEmail(email);

    customer.getPhones().addPhone(phone);
    customer.getPhones().addPhone(phone);

    // Creating the customer
    CustomerService.createCustomer(customer);

    // Creating the loans and assigning them to the customer
    Loan loan = TestEntityCreator.createBasicLoan(customer);
    Loan loantwo = TestEntityCreator.createBasicLoan(customer);
    customer.getLoans().addLoan(loan);
    customer.getLoans().addLoan(loantwo);

    // Creating the loans in the mainframe
    CustomerService.updateCustomer(customer);

    // Act
    String customerName = customer.getName();
    customer.setName("New Name");
    CustomerService.updateCustomer(customer);

    // Assert
    assertNotEquals(customerName, customer.getName());
    assertEquals(2, customer.getLoans().getLoanCount());
  }
}
