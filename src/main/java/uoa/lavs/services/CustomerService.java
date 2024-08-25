package uoa.lavs.services;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Emails;
import uoa.lavs.models.Employers;
import uoa.lavs.models.Loans;
import uoa.lavs.models.Phones;
import uoa.lavs.repository.CustomerRepository;
import uoa.lavs.utils.objects.ValidationException;

public class CustomerService implements IService {

  private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

  public static void createCustomer(Customer newCustomer)
      throws RuntimeException, ValidationException {
    newCustomer.validate();
    String customerId = CustomerRepository.create(newCustomer).getId();
    newCustomer.setId(customerId);

    AddressService.createAddressesFromCustomer(newCustomer);
    PhoneService.createPhonesFromCustomer(newCustomer);
    EmailService.createEmailsFromCustomer(newCustomer);
    EmployerService.createEmployersFromCustomer(newCustomer);
    NoteService.updateNotesFromCustomer(newCustomer);
  }

  public static Customer getCustomer(String id) throws RuntimeException {
    Customer customer = CustomerRepository.get(id);
    Addresses addresses = AddressService.getAddresses(customer);
    Phones phones = PhoneService.getPhones(customer);
    Emails emails = EmailService.getEmails(customer);
    Employers employers = EmployerService.getEmployers(customer);
    String notes = NoteService.getNotes(customer);
    Loans loans = LoanService.getLoans(customer);

    customer.setAddresses(addresses);
    customer.setPhones(phones);
    customer.setEmails(emails);
    customer.setEmployers(employers);
    customer.setNotes(notes);
    customer.setLoans(loans);

    return customer;
  }

  public static void updateCustomer(Customer newCustomer) throws RuntimeException {
    newCustomer.validate();
    CustomerRepository.update(newCustomer);

    AddressService.updateAddressesFromCustomer(newCustomer);
    PhoneService.updatePhonesFromCustomer(newCustomer);
    EmailService.updateEmailsFromCustomer(newCustomer);
    EmployerService.updateEmployersFromCustomer(newCustomer);
    NoteService.updateNotesFromCustomer(newCustomer);
  }

  public static List<Customer> getCustomerListByName(String name) throws RuntimeException {
    return CustomerRepository.getCustomersByName(name);
  }
}
