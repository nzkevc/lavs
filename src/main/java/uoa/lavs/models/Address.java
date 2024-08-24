package uoa.lavs.models;

import uoa.lavs.utils.objects.ValidationException;

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
  private boolean isPrimary;
  private boolean isMailing;

  public Address() {}

  public Address(String customerID, Integer number) {
    this.customerID = customerID;
    this.number = number;
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

  public boolean getPrimary() {
    return isPrimary;
  }

  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
  }

  public boolean getMailing() {
    return isMailing;
  }

  public void setIsMailing(Boolean isMailing) {
    this.isMailing = isMailing;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    if (o instanceof Address) {
      Address address = (Address) o;

      if ((this.type == null && address.type == null) || (this.type == address.type)) {
      } else {
        return false;
      }

      if ((this.line1 == null && address.line1 == null) || (this.line1 == address.line1)) {
      } else {
        return false;
      }

      if ((this.line2 == null && address.line2 == null) || (this.line2 == address.line2)) {
      } else {
        return false;
      }

      if ((this.suburb == null && address.suburb == null) || (this.suburb == address.suburb)) {
      } else {
        return false;
      }

      if ((this.city == null && address.city == null) || (this.city == address.city)) {
      } else {
        return false;
      }

      if ((this.postcode == null && address.postcode == null)
          || (this.postcode == address.postcode)) {
      } else {
        return false;
      }

      if ((this.country == null && address.country == null) || (this.country == address.country)) {
      } else {
        return false;
      }

      if (this.isPrimary != address.isPrimary) {
        return false;
      }

      if (this.isMailing != address.isMailing) {
        return false;
      }

      return true;
    }
    return false;
  }

  // TODO
  @Override
  public boolean validate() throws ValidationException {
    if (validateCustomerId(customerID)
        && validateType(type)
        && validateLine1(line1)
        && validateLine2(line2)
        && validateSuburb(suburb)
        && validateCity(city)
        && validatePostcode(postcode)
        && validateCountry(country)) {
      return true;
    } else {
      throw new ValidationException("Address validation failed.");
    }
  }

  public boolean validateCustomerId(String customerId) {
    if (customerId == null) {
      return false;
    }
    return customerId.length() <= 10 && !customerId.isEmpty();
  }

  public boolean validateType(String type) {
    if (type == null) {
      return false;
    }
    return type.length() <= 20 && !type.isEmpty();
  }

  public boolean validateLine1(String line1) {
    if (line1 == null) {
      return false;
    }
    return line1.length() <= 60 && !line1.isEmpty();
  }

  // this means line2 is optional
  public boolean validateLine2(String line2) {
    if (line2 == null) {
      return true;
    }
    return line2.length() <= 60;
  }

  public boolean validateSuburb(String suburb) {
    if (suburb == null) {
      return false;
    }
    return suburb.length() <= 30 && !suburb.isEmpty();
  }

  public boolean validateCity(String city) {
    if (city == null) {
      return false;
    }
    return city.length() <= 30 && !city.isEmpty();
  }

  public boolean validatePostcode(String postcode) {
    if (postcode == null) {
      return false;
    }
    return postcode.length() <= 10 && !postcode.isEmpty();
  }

  public boolean validateCountry(String country) {
    if (country == null) {
      return false;
    }
    return country.length() <= 30 && !country.isEmpty();
  }
}
