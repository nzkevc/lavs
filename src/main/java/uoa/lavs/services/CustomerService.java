package uoa.lavs.services;

import uoa.lavs.exceptions.ValidationException;
import uoa.lavs.models.*;
import uoa.lavs.repository.CustomerRepository;
import uoa.lavs.repository.EmployerRepository;

public class CustomerService implements IService {

  public static void createCustomer(Customer customer)
      throws RuntimeException, ValidationException {
    customer.validate();
    CustomerRepository.create(customer);
    AddressService.createAddressesFromCustomer(customer);
    PhoneService.createPhonesFromCustomer(customer);
    EmailService.createEmailsFromCustomer(customer);
    NoteService.createNotesFromCustomer(customer);

    if (customer.getLoans() != null) {
      LoanService.createLoansFromCustomer(customer);
    }
  }

  public static Customer getCustomer(String id) throws RuntimeException {
    Customer customer = CustomerRepository.get(id);
    Addresses addresses = AddressService.getAddresses(customer);
    Phones phones = PhoneService.getPhones(customer);
    Emails emails = EmailService.getEmails(customer);
    String notes = NoteService.getNotes(customer);
    Loans loans = LoanService.getLoans(customer);

    // may change in the future to support multiple employers
    Employer employer = EmployerRepository.get(id, 1);
    customer.setEmployer(employer);

    customer.setAddresses(addresses);
    customer.setPhones(phones);
    customer.setEmails(emails);
    customer.setNotes(notes);
    customer.setLoans(loans);

    return customer;
  }

  public static void updateCustomer(Customer customer) throws RuntimeException {
    CustomerRepository.update(customer);
  }
}
