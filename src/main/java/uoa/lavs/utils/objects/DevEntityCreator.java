package uoa.lavs.utils.objects;

import java.time.LocalDate;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.models.Address;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Email;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Loan;
import uoa.lavs.models.Phone;

// Nate: copy of Adam's TestEntityCreator - temporary for now
public class DevEntityCreator {

  // This method is new - assumes one address, phone, email, employer, and loan
  public static Customer createFullCustomer() {
    Customer customer = createBasicCustomer();
    customer.getAddresses().addAddress(createBasicPrimaryAddress(customer));
    customer.getPhones().addPhone(createBasicPrimaryPhone(customer));
    customer.getEmails().addEmail(createBasicEmail(customer));
    customer.getEmployers().addEmployer(createBasicEmployer(customer));
    customer.getLoans().addLoan(createBasicLoan(customer));
    customer.setId(null);
    return customer;
  }

  public static Customer createBasicCustomer() {
    LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
    Customer customer = new Customer();
    customer.setId("1");
    customer.setTitle("Mr");
    customer.setName("John Doe");
    customer.setDateOfBirth(dateOfBirth);
    customer.setOccupation("Consultant");
    customer.setCitizenship("New Zealand");
    customer.setVisa("N/A");
    return customer;
  }

  public static Address createBasicAddress(Customer customer) {
    Address address = new Address();
    address.setCustomerId(customer.getId());
    address.setType("Home");
    address.setLine1("123 Fake St");
    address.setSuburb("Fakeville");
    address.setCity("Faketown");
    address.setPostCode("1234");
    address.setCountry("New Zealand");
    address.setIsPrimary(true);
    address.setIsMailing(true);
    return address;
  }

  public static Address createBasicPrimaryAddress(Customer customer) {
    Address address = new Address();
    address.setCustomerId(customer.getId());
    address.setType("Home");
    address.setLine1("123 Fake St");
    address.setSuburb("Fakeville");
    address.setCity("Faketown");
    address.setPostCode("1234");
    address.setCountry("New Zealand");
    address.setIsPrimary(true);
    address.setIsMailing(false);
    return address;
  }

  public static Address createBasicMailingAddress(Customer customer) {
    Address address = new Address();
    address.setCustomerId(customer.getId());
    address.setType("Home");
    address.setLine1("123 Faker Ln");
    address.setSuburb("Fakerton");
    address.setCity("Fakecity");
    address.setPostCode("1234");
    address.setCountry("New Zealand");
    address.setIsPrimary(false);
    address.setIsMailing(true);
    return address;
  }

  public static Address createBasicSecondaryAddress(Customer customer) {
    Address address = new Address();
    address.setCustomerId(customer.getId());
    address.setType("Home");
    address.setLine1("321 Fakes Rd");
    address.setSuburb("Fakecity");
    address.setCity("Fakecity");
    address.setPostCode("2312");
    address.setCountry("New Zealand");
    address.setIsPrimary(false);
    address.setIsMailing(false);
    return address;
  }

  public static Email createBasicEmail(Customer customer) {
    Email email = new Email();
    email.setCustomerId(customer.getId());
    email.setAddress("fakeEmail@fake.com");
    email.setIsPrimary(true);
    return email;
  }

  public static Email createBasicNonPrimaryEmail(Customer customer) {
    Email email = new Email();
    email.setCustomerId(customer.getId());
    email.setAddress("fakeEmail2@fake.com");
    email.setIsPrimary(false);
    return email;
  }

  public static Phone createBasicPhone(Customer customer) {
    Phone phone = new Phone(customer.getId(), null);
    phone.setType("Mobile");
    phone.setPrefix("021");
    phone.setPhoneNumber("1234567");
    phone.setPrimary(true);
    phone.setCanSendTxt(true);
    return phone;
  }

  public static Phone createBasicPrimaryPhone(Customer customer) {
    Phone phone = new Phone(customer.getId(), null);
    phone.setType("Home");
    phone.setPrefix("09");
    phone.setPhoneNumber("1234567");
    phone.setPrimary(true);
    phone.setCanSendTxt(false);
    return phone;
  }

  public static Phone createBasicTextPhone(Customer customer) {
    Phone phone = new Phone(customer.getId(), null);
    phone.setType("Mobile");
    phone.setPrefix("022");
    phone.setPhoneNumber("7654321");
    phone.setPrimary(false);
    phone.setCanSendTxt(true);
    return phone;
  }

  public static Phone createBasicSecondaryPhone(Customer customer) {
    Phone phone = new Phone(customer.getId(), null);
    phone.setType("Work");
    phone.setPrefix("09");
    phone.setPhoneNumber("123987");
    phone.setPrimary(false);
    phone.setCanSendTxt(false);
    return phone;
  }

  public static Employer createBasicEmployer(Customer customer) {
    return new Employer(
        customer.getId(),
        "Fake Employer",
        createBasicAddress(customer),
        createBasicPhone(customer),
        createBasicEmail(customer),
        "www.fakeemployer.com",
        true);
  }

  public static Loan createBasicLoan(Customer customer) {
    LocalDate startDate = LocalDate.of(1990, 1, 1);
    Loan loan = new Loan();
    loan.setCustomerId(customer.getId());
    loan.setLoanId(null);
    loan.setCustomerName(customer.getName());
    loan.setPrincipleCents(1000.00);
    loan.setStartDate(startDate);
    loan.setPeriodMonths(10);
    loan.setTerm(10);
    loan.setInterestRate(0.05);
    loan.setRateType(RateType.Floating);
    loan.setCompoundingFrequency(Frequency.Weekly);
    loan.setPaymentAmountCents(100.00);
    loan.setPaymentFrequency(Frequency.Monthly);
    loan.setInterestOnly(true);
    return loan;
  }

  public static Loan createBasicSecondaryLoan(Customer customer) {
    LocalDate startDate = LocalDate.of(1991, 2, 2);
    Loan loan = new Loan();
    loan.setCustomerId(customer.getId());
    loan.setLoanId(null);
    loan.setCustomerName(customer.getName());
    loan.setPrincipleCents(2000.00);
    loan.setStartDate(startDate);
    loan.setPeriodMonths(20);
    loan.setTerm(10);
    loan.setInterestRate(0.10);
    loan.setRateType(RateType.Fixed);
    loan.setCompoundingFrequency(Frequency.Monthly);
    loan.setPaymentAmountCents(200.00);
    loan.setPaymentFrequency(Frequency.Fortnightly);
    loan.setInterestOnly(false);
    return loan;
  }
}
