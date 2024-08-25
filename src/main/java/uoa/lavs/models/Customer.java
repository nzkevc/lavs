package uoa.lavs.models;

import java.time.LocalDate;
import uoa.lavs.utils.objects.ValidationException;

public class Customer implements IModel<Customer> {

  private String id;
  private String status;
  private String title;
  private String name;
  private LocalDate dateOfBirth;
  private String occupation;
  private String citizenship;
  private String visa;
  private Addresses addresses = new Addresses(id);
  private Phones phones = new Phones(id);
  private Emails emails = new Emails(id);
  private Employer employer;
  private String notes;
  private Loans loans = new Loans(id);

  public Customer() {}

  public Customer(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getCitizenship() {
    return citizenship;
  }

  public void setCitizenship(String citizenship) {
    this.citizenship = citizenship;
  }

  public String getVisa() {
    return visa;
  }

  public void setVisa(String visa) {
    this.visa = visa;
  }

  public Addresses getAddresses() {
    return addresses;
  }

  public void setAddresses(Addresses addresses) {
    this.addresses = addresses;
  }

  public Phones getPhones() {
    return phones;
  }

  public void setPhones(Phones phones) {
    this.phones = phones;
  }

  public Emails getEmails() {
    return emails;
  }

  public void setEmails(Emails emails) {
    this.emails = emails;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public String getNotes() {
    return notes;
  }

  public String setNotes(String notes) {
    this.notes = notes;
    return notes;
  }

  public Loans getLoans() {
    return loans;
  }

  public void setLoans(Loans loans) {
    this.loans = loans;
  }

  // TODO
  @Override
  public boolean validate() throws ValidationException {
    return true;
  }

  @Override
  public String toString() {
    return "%s: ID = %s".formatted(name, id);
  }
}
