package uoa.lavs.models;

import java.time.LocalDate;

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

  public static boolean validateCustomerId(String id) {
    return id.length() <= 10 && !id.isEmpty();
  }

  public boolean validateTitle(String title) {
    if (title == null) {
      return false;
    }
    return title.length() <= 10 && !title.isEmpty();
  }

  public boolean validateName(String name) {
    if (name == null) {
      return false;
    }
    return name.length() <= 60 && !name.isEmpty();
  }

  public boolean validateDateOfBirth(LocalDate dateOfBirth) {
    return dateOfBirth != null && dateOfBirth.isBefore(LocalDate.now());
  }

  public boolean validateOccupation(String occupation) {
    if (occupation == null) {
      return false;
    }
    return occupation.length() <= 40 && !occupation.isEmpty();
  }

  public boolean validateCitizenship(String citizenship) {
    if (citizenship == null) {
      return false;
    }
    return citizenship.length() <= 40 && !citizenship.isEmpty();
  }

  // visa is optional
  public boolean validateVisa(String visa) {
    if (visa == null) {
      return true;
    }
    return visa.length() <= 40;
  }

  public boolean validateNotes(String notes) {
    if (notes == null || notes.isEmpty()) {
      return true;
    }
    String[] lines = notes.split("\n");
    if (lines.length > 19) {
      return false;
    }
    for (String line : lines) {
      if (line.length() > 70) {
        return false;
      }
    }
    return true;
  }
}
