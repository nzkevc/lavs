package uoa.lavs.models;

import uoa.lavs.utils.ValidationUtils;
import uoa.lavs.utils.objects.ValidationException;

public class Address implements IModel {
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

  public void validateCustomerId(String customerId) throws ValidationException {
    ValidationUtils.validateFieldExists(customerId);
    Customer.validateCustomerId(customerId);
  }

  public static void validateType(String type) throws ValidationException {
    ValidationUtils.validateFieldExists(type);
    if (type.length() > 20) {
      throw new ValidationException("Type must be between 1 and 20 characters.");
    }
  }

  public static void validateLine1(String line1) throws ValidationException {
    ValidationUtils.validateFieldExists(line1);
    if (line1.length() > 60) {
      throw new ValidationException("Line 1 must be between 1 and 60 characters.");
    }
  }

  // this means line2 is optional
  public static void validateLine2(String line2) throws ValidationException {
    ValidationUtils.validateFieldExists(
        line2, "Line 2 field requires a value - leave N/A if not applicable.");
    if (line2.length() > 60) {
      throw new ValidationException("Line 2 must be between 1 and 60 characters.");
    }
  }

  public static void validateSuburb(String suburb) throws ValidationException {
    ValidationUtils.validateFieldExists(suburb);
    if (suburb.length() > 30) {
      throw new ValidationException("Suburb must be between 1 and 30 characters.");
    }
  }

  public static void validateCity(String city) throws ValidationException {
    ValidationUtils.validateFieldExists(city);
    if (city.length() > 30) {
      throw new ValidationException("City must be between 1 and 30 characters.");
    }
  }

  public static void validatePostcode(String postcode) throws ValidationException {
    ValidationUtils.validateFieldExists(postcode);
    if (postcode.length() > 10) {
      throw new ValidationException("Postcode must be between 1 and 10 characters.");
    }
    if (!postcode.matches("^\\d+$")) {
      throw new ValidationException("Postcode must be a number.");
    }
  }

  public static void validateCountry(String country) throws ValidationException {
    ValidationUtils.validateFieldExists(country);
    if (country.length() > 30) {
      throw new ValidationException("Country must be between 1 and 30 characters.");
    }
  }
}
