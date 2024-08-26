package uoa.lavs.models;

import java.time.LocalDate;
import uoa.lavs.utils.ValidationUtils;
import uoa.lavs.utils.objects.ValidationException;

public class Customer implements IModel {

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
  private Employers employers = new Employers(id);
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

  public Employers getEmployers() {
    return employers;
  }

  public void setEmployers(Employers employers) {
    this.employers = employers;
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

  @Override
  public String toString() {
    return "%s: ID = %s".formatted(name, id);
  }

  /**
   * Validates the customer ID. ONLY CALL WHEN UPDATING AN EXISTING CUSTOMER/CUSTOMER FIELD. If
   * creating a new customer, customerId would be set to null.
   *
   * @param id
   * @throws ValidationException
   */
  public static void validateCustomerId(String id) throws ValidationException {
    if (id.length() > 10) {
      throw new ValidationException("Customer ID must be between 1 and 10 characters.");
    }
  }

  public static void validateTitle(String title) throws ValidationException {
    ValidationUtils.validateFieldExists(title);
    if (title.length() > 10) {
      throw new ValidationException("Title must be between 1 and 10 characters.");
    }
  }

  public static void validateName(String name) throws ValidationException {
    ValidationUtils.validateFieldExists(name);
    if (name.length() > 60) {
      throw new ValidationException("Name must be between 1 and 60 characters.");
    }
  }

  public static void validateDateOfBirth(LocalDate dateOfBirth) throws ValidationException {
    if (dateOfBirth == null) {
      throw new ValidationException("Date of Birth is required.");
    } else if (dateOfBirth.isAfter(LocalDate.now())) {
      throw new ValidationException("Date of Birth cannot be in the future.");
    }
  }

  public static void validateOccupation(String occupation) throws ValidationException {
    ValidationUtils.validateFieldExists(occupation);
    if (occupation.length() > 40) {
      throw new ValidationException("Occupation must be between 1 and 40 characters.");
    }
  }

  public static void validateCitizenship(String citizenship) throws ValidationException {
    ValidationUtils.validateFieldExists(citizenship);
    if (citizenship.length() > 40) {
      throw new ValidationException("Citizenship must be between 1 and 40 characters.");
    }
  }

  // visa is optional
  public static void validateVisa(String visa) throws ValidationException {
    ValidationUtils.validateFieldExists(
        visa, "Field requires a value - leave N/A if not applicable.");
    if (visa.length() > 40) {
      throw new ValidationException("Visa must be between 1 and 40 characters.");
    }
  }

  public static void validateNotes(String notes) throws ValidationException {
    if (notes != null && !notes.isEmpty()) {
      String[] lines = notes.split("\n");
      if (lines.length > 19) {
        throw new ValidationException("Notes must be less than 20 lines.");
      }
      for (String line : lines) {
        if (line.length() > 70) {
          throw new ValidationException("Each line of notes must be less than 70 characters.");
        }
      }
    }
  }
}
