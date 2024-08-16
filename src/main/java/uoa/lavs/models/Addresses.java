package uoa.lavs.models;

import java.util.ArrayList;
import java.util.List;

public class Addresses {
  private String customerId;
  private List<Address> addresses;
  private Address residentialAddress;
  private Address mailingAddress;

  public Addresses(String customerId) {
    this.customerId = customerId;
    this.addresses = new ArrayList<>();
  }

  public Addresses(String customerId, Address residentAddress) {
    this.customerId = customerId;
    this.addresses = new ArrayList<>();
    this.residentialAddress = residentAddress;
    this.mailingAddress = residentAddress;
    this.addresses.add(residentAddress);
  }

  public Addresses(String customerId, Address residentAddress, Address mailAddress) {
    this.customerId = customerId;
    this.addresses = new ArrayList<>();
    this.residentialAddress = residentAddress;
    this.mailingAddress = mailAddress;
    this.addresses.add(residentAddress);
    this.addresses.add(mailAddress);
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void addAddress(Address newAddress) {
    addresses.add(newAddress);
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public Integer getAddressCount() {
    return addresses.size();
  }

  public Address getResidentialAddress() {
    return residentialAddress;
  }

  public void setResidentialAddress(Address address) {
    if (residentialAddress != null) {
      residentialAddress.setIsPrimary(false);
    }
    residentialAddress = address;
    addresses.add(address);
  }

  public Address getMailingAddress() {
    return mailingAddress;
  }

  public void setMailingAddress(Address address) {
    if (mailingAddress != null) {
      mailingAddress.setIsMailing(false);
    }
    mailingAddress = address;
    addresses.add(address);
  }
}
