package uoa.lavs.models;

import java.util.HashSet;
import java.util.Set;
import uoa.lavs.utils.objects.ValidationException;

public class Addresses {
  private String customerId;
  private Set<Address> addresses;
  private Address primaryAddress;
  private Address mailingAddress;

  public Addresses(String customerId) {
    this.customerId = customerId;
    this.addresses = new HashSet<>();
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
        setPrimaryAddress(newAddress);
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

  public Address getPrimaryAddress() {
    return primaryAddress;
  }

  void setPrimaryAddress(Address address) {
    if (address != null) {
      if (primaryAddress != null) {
        primaryAddress.setIsPrimary(false);
      }
      primaryAddress = address;
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

  public boolean validate() throws ValidationException {
    if (primaryAddress != null && primaryAddress.getPrimary()) {
      return true;
    } else {
      throw new ValidationException("Primary address is required");
    }
  }
}
