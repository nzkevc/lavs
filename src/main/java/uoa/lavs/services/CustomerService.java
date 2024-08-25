package uoa.lavs.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Emails;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Loans;
import uoa.lavs.models.Phones;
import uoa.lavs.repository.CustomerRepository;
import uoa.lavs.repository.EmployerRepository;
import uoa.lavs.utils.objects.ValidationException;

public class CustomerService implements IService {

  private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

  public static void createCustomer(Customer newCustomer)
      throws RuntimeException, ValidationException {
    newCustomer.validate();
    String customerId = CustomerRepository.create(newCustomer).getId();
    newCustomer.setId(customerId);

    Employer employer = newCustomer.getEmployer();
    if (employer != null) {
      employer.setCustomerId(newCustomer.getId());
      EmployerRepository.create(employer);
    }

    AddressService.createAddressesFromCustomer(newCustomer);
    PhoneService.createPhonesFromCustomer(newCustomer);
    EmailService.createEmailsFromCustomer(newCustomer);
    NoteService.updateNotesFromCustomer(newCustomer);
  }

  public static Customer getCustomer(String id) throws RuntimeException {
    Customer customer = CustomerRepository.get(id);
    Addresses addresses = AddressService.getAddresses(customer);
    Phones phones = PhoneService.getPhones(customer);
    Emails emails = EmailService.getEmails(customer);
    String notes = NoteService.getNotes(customer);
    Loans loans = LoanService.getLoans(customer);

    // may change in the future to support multiple employers
    try {
      Employer employer = EmployerRepository.get(id, 1);
      customer.setEmployer(employer);
    } catch (Exception e) {
      logger.error("Failed to get employer for customer: " + e.getMessage());
    }

    customer.setAddresses(addresses);
    customer.setPhones(phones);
    customer.setEmails(emails);
    customer.setNotes(notes);
    customer.setLoans(loans);

    return customer;
  }

  public static void updateCustomer(Customer newCustomer) throws RuntimeException {
    newCustomer.validate();
    CustomerRepository.update(newCustomer);

    Employer employer = newCustomer.getEmployer();
    if (employer != null) {
      employer.setCustomerId(newCustomer.getId());
      EmployerRepository.update(employer);
    }

    AddressService.updateAddressesFromCustomer(newCustomer);
    PhoneService.updatePhonesFromCustomer(newCustomer);
    EmailService.updateEmailsFromCustomer(newCustomer);
    NoteService.updateNotesFromCustomer(newCustomer);
  }

  public static List<Customer> getCustomerListByName(String name) throws RuntimeException {
    return CustomerRepository.getCustomersByName(name);
  }
}
