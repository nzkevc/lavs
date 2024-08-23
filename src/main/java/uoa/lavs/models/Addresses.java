package uoa.lavs.models;

import java.util.HashSet;
import java.util.Set;

public class Addresses {
  private String customerId;
  private Set<Address> addresses;
  private Address residentialAddress;
  private Address mailingAddress;

  public Addresses(String customerId) {
    this.customerId = customerId;
    this.addresses = new HashSet<>();
  }

  public Addresses(Set<Address> addresses) {
    this.addresses = addresses;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void addAddress(Address newAddress) {
    if (newAddress != null) {
      if (newAddress.getPrimary()) {
        setResidentialAddress(newAddress);
      }

      if (newAddress.getMailing()) {
        setMailingAddress(newAddress);
      }

      if (!newAddress.getPrimary() && !newAddress.getMailing()) {
        addresses.add(newAddress);
      }
    }
  }

  public Set<Address> getAddresses() {
    return addresses;
  }

  public Integer getAddressCount() {
    return addresses.size();
  }

  public Address getResidentialAddress() {
    return residentialAddress;
  }

  void setResidentialAddress(Address address) {
    if (address != null) {
      if (residentialAddress != null) {
        residentialAddress.setIsPrimary(false);
      }
      residentialAddress = address;
      addresses.add(address);
    }
  }

  public Address getMailingAddress() {
    return mailingAddress;
  }

  void setMailingAddress(Address address) {
    if (address != null) {
      if (mailingAddress != null) {
        mailingAddress.setIsMailing(false);
      }
      mailingAddress = address;
      addresses.add(address);
    }
  }
}
