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

  public Address(String customerID, Integer number) {
    this.customerID = customerID;
    this.number = number;
  }

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

  public String getCustomerId() {
    return customerID;
  }

  public void setCustomerId(String customerId) {
    this.customerID = customerId;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getLine1() {
    return line1;
  }

  public void setLine1(String line1) {
    this.line1 = line1;
  }

  public String getLine2() {
    return line2;
  }

  public void setLine2(String line2) {
    this.line2 = line2;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setSuburb(String suburb) {
    this.suburb = suburb;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getPostCode() {
    return postcode;
  }

  public void setPostCode(String postcode) {
    this.postcode = postcode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Boolean getPrimary() {
    return isPrimary;
  }

  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
  }

  public Boolean getMailing() {
    return isMailing;
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

  // TODO
  @Override
  public boolean validate() {
    return true;
  }
}
