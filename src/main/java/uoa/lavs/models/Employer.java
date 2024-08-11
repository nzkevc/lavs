package uoa.lavs.models;

public class Employer {
  private String name;
  private Address address;
  private Email email;
  private String website;
  private boolean isOwner;

  public Employer(String name, Address address, Email email, String website, boolean isOwner) {
    this.name = name;
    this.address = address;
    this.email = email;
    this.website = website;
    this.isOwner = isOwner;
  }

  public String getName() {
    return name;
  }

  public Address getAddress() {
    return address;
  }

  public Email getEmail() {
    return email;
  }

  public String getWebsite() {
    return website;
  }

  public boolean isOwner() {
    return isOwner;
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
}
