package uoa.lavs.services;

import uoa.lavs.exceptions.ValidationException;
import uoa.lavs.models.*;
import uoa.lavs.repository.CustomerRepository;
import uoa.lavs.repository.EmployerRepository;

public class CustomerService implements IService {

  public static void createCustomer(Customer newCustomer)
      throws RuntimeException, ValidationException {
    newCustomer.validate();
    CustomerRepository.create(newCustomer);
    AddressService.createAddressesFromCustomer(newCustomer);
    PhoneService.createPhonesFromCustomer(newCustomer);
    EmailService.createEmailsFromCustomer(newCustomer);
    NoteService.createNotesFromCustomer(newCustomer);

    if (newCustomer.getLoans().getLoanCount() > 0) {
      LoanService.createLoansFromCustomer(newCustomer);
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
    try {
      Employer employer = EmployerRepository.get(id, 1);
      customer.setEmployer(employer);
    } catch (Exception e) {
      // do nothing
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
    AddressService.updateAddressesFromCustomer(newCustomer);
    PhoneService.updatePhonesFromCustomer(newCustomer);
    EmailService.updateEmailsFromCustomer(newCustomer);
    NoteService.updateNotesFromCustomer(newCustomer);
    LoanService.updateLoansFromCustomer(newCustomer);
  }
}
