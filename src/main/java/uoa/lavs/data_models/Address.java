package uoa.lavs.data_models;

public class Address {
  private String type;
  private String line1;
  private String line2;
  private String suburb;
  private String city;
  private String postcode;
  private String country;

  public Address(
      String type,
      String line1,
      String line2,
      String suburb,
      String city,
      String postcode,
      String country) {
    this.type = type;
    this.line1 = line1;
    this.line2 = line2;
    this.suburb = suburb;
    this.city = city;
    this.postcode = postcode;
    this.country = country;
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
}
