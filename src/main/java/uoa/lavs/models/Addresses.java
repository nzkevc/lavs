package uoa.lavs.models;

import java.util.HashSet;
import java.util.Set;
import uoa.lavs.utils.objects.ValidationException;

public class Addresses implements IModel {
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

  public static void validate(Addresses addresses) throws ValidationException {
    if (addresses.getAddresses().isEmpty()) {
      return;
    }
    if (addresses.getAddresses().stream().noneMatch(Address::getPrimary)) {
      throw new ValidationException("Primary address must be set.");
    }
    if (addresses.getAddresses().stream().filter(Address::getPrimary).count() > 1) {
      throw new ValidationException("Only one primary address is allowed.");
    }
    if (addresses.getAddresses().stream().noneMatch(Address::getMailing)) {
      throw new ValidationException("Mailing address must be set.");
    }
    if (addresses.getAddresses().stream().filter(Address::getMailing).count() > 1) {
      throw new ValidationException("Only one mailing address is allowed.");
    }
  }
}
