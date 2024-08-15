package uoa.lavs.models;


public class Address implements IModel<Address> {
  private String customerID;
  private Integer number;
  private String type;
  private String line1;
  private String line2;
  private String suburb;
  private String city;
  private String postcode;
  private String country;
  private Boolean isPrimary;
  private Boolean isMailing;

  public Address(
      String customerID,
      Integer number,
      String type,
      String line1,
      String line2,
      String suburb,
      String city,
      String postcode,
      String country,
      Boolean isPrimary,
      Boolean isMailing) {
    this.customerID = customerID;
    this.number = number;
    this.type = type;
    this.line1 = line1;
    this.line2 = line2;
    this.suburb = suburb;
    this.city = city;
    this.postcode = postcode;
    this.country = country;
    this.isPrimary = isPrimary;
    this.isMailing = isMailing;
  }

  public String getCustomerID() {
    return customerID;
  }

  public Integer getNumber() {
    return number;
  }

  public String getType() {
    return type;
  }

  public String getLine1() {
    return line1;
  }

  public String getLine2() {
    return line2;
  }

  public String getSuburb() {
    return suburb;
  }

  public String getCity() {
    return city;
  }

  public String getPostcode() {
    return postcode;
  }

  public String getCountry() {
    return country;
  }

  public Boolean getPrimary() {
    return isPrimary;
  }

  public Boolean getMailing() {
    return isMailing;
  }

  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
  }

  public void setIsMailing(Boolean isMailing) {
    this.isMailing = isMailing;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (o instanceof Address) {
      Address address = (Address) o;
      return type.equals(address.type)
          && line1.equals(address.line1)
          && line2.equals(address.line2)
          && suburb.equals(address.suburb)
          && city.equals(address.city)
          && postcode.equals(address.postcode)
          && country.equals(address.country);
    }
    return false;
  }

  /** Checks if the customer has an address registered */
  @Override
  public boolean validate() {
    return true;
  }
}
