package uoa.lavs.models;

import uoa.lavs.utils.objects.ValidationException;

public class Employer implements IModel<Employer> {
  private String customerId;
  private Integer number;
  private String name;
  private Address address;
  private Phone phone;
  private Email email;
  private String website;
  private boolean isOwner;

  public Employer() {}

  public Employer(String customerId, Integer number) {
    this.customerId = customerId;
    this.number = number;
    // Address, phone, and email are not stored as their own entities in the database for employers,
    // so customerId and number fields are null
    this.address = new Address(null, null);
    this.phone = new Phone(null, null);
    this.email = new Email(null, null);
  }

  public Employer(
      String customerId,
      String name,
      Address address,
      Phone phone,
      Email email,
      String website,
      boolean isOwner) {
    this.customerId = customerId;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.website = website;
    this.isOwner = isOwner;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Phone getPhone() {
    return phone;
  }

  public void setPhone(Phone phone) {
    this.phone = phone;
  }

  public Email getEmail() {
    return email;
  }

  public void setEmail(Email email) {
    this.email = email;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public boolean isOwner() {
    return isOwner;
  }

  public void setOwner(boolean owner) {
    isOwner = owner;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (o instanceof Employer) {
      Employer employer = (Employer) o;
      return name.equals(employer.getName())
          && address.equals(employer.getAddress())
          && email.equals(employer.getEmail())
          && isOwner == employer.isOwner();
    }
    return false;
  }

  // TODO:
  @Override
  public boolean validate() throws ValidationException {
    return true;
  }
}
