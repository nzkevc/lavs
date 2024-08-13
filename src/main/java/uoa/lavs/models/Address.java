package uoa.lavs.models;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.messages.customer.FindCustomerAddress;
import uoa.lavs.mainframe.messages.customer.LoadCustomerAddress;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerAddress;

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
    Connection connection = Instance.getConnection();

    if (customerID == null || customerID.length() > 10 || customerID.length() == 0) {
      return false;
    }

    FindCustomerAddress findCustomerAddress = new FindCustomerAddress();
    findCustomerAddress.setCustomerId(customerID);

    return findCustomerAddress.send(connection).getWasSuccessful();
  }

  /** Getting the number of addresses a customer has registered to them */
  public int getCountFromServer() {
    Connection connection = Instance.getConnection();

    if (customerID == null || customerID.length() > 10 || customerID.length() == 0) {
      return 0;
    }

    FindCustomerAddress findCustomerAddress = new FindCustomerAddress();
    findCustomerAddress.setCustomerId(customerID);

    if (findCustomerAddress.send(connection).getWasSuccessful()) {
      return findCustomerAddress.getCountFromServer();
    }

    return 0;
  }

  /** Adding new address or updating existing address in the mainframe */
  @Override
  public Address persist() {
    Connection connection = Instance.getConnection();

    UpdateCustomerAddress updateCustomerAddress = new UpdateCustomerAddress();
    if (customerID != null && customerID.length() <= 10) {
      updateCustomerAddress.setCustomerId(customerID);
    } else return null;

    if (number != null) {
      updateCustomerAddress.setNumber(number);
    } else return null;

    if (type != null) {
      updateCustomerAddress.setType(type);
    } else return null;

    if (line1 != null) {
      updateCustomerAddress.setLine1(line1);
    } else return null;

    if (line2 != null) {
      updateCustomerAddress.setLine2(line2);
    } else return null;

    if (suburb != null) {
      updateCustomerAddress.setSuburb(suburb);
    } else return null;

    if (city != null) {
      updateCustomerAddress.setCity(city);
    } else return null;

    if (postcode != null) {
      updateCustomerAddress.setPostCode(postcode);
    } else return null;

    if (country != null) {
      updateCustomerAddress.setCountry(country);
    } else return null;

    if (isPrimary != null) {
      updateCustomerAddress.setIsPrimary(isPrimary);
    } else return null;

    if (updateCustomerAddress.send(connection).getWasSuccessful()) {
      return this;
    }

    return null;
  }

  @Override
  public void delete() {}

  @Override
  public Address get(String id) {
    Connection connection = Instance.getConnection();

    LoadCustomerAddress loadCustomerAddress = new LoadCustomerAddress();
    if (customerID != null && customerID.length() <= 10) {
      loadCustomerAddress.setCustomerId(customerID);
    } else return null;

    if (number != null) {
      loadCustomerAddress.setNumber(number);
    } else return null;

    if (loadCustomerAddress.send(connection).getWasSuccessful()) {
      this.city = loadCustomerAddress.getCityFromServer();
      this.country = loadCustomerAddress.getCountryFromServer();
      this.line1 = loadCustomerAddress.getLine1FromServer();
      this.line2 = loadCustomerAddress.getLine2FromServer();
      this.suburb = loadCustomerAddress.getSuburbFromServer();
      this.type = loadCustomerAddress.getTypeFromServer();
      this.isPrimary = loadCustomerAddress.getIsPrimaryFromServer();
      this.isMailing = loadCustomerAddress.getIsMailingFromServer();
      return this;
    }

    return null;
  }
}
