package uoa.lavs.models;

import java.util.ArrayList;
import java.util.List;

public class Addresses {
  private List<Address> addresses;
  private Address residentialAddress;
  private Address mailingAddress;

  public Addresses(Address residentAddress) {
    this.addresses = new ArrayList<>();
    this.residentialAddress = residentAddress;
    this.mailingAddress = residentAddress;
    this.addresses.add(residentAddress);
  }

  public Addresses(Address residentAddress, Address mailAddress) {
    this.addresses = new ArrayList<>();
    this.residentialAddress = residentAddress;
    this.mailingAddress = mailAddress;
    this.addresses.add(residentAddress);
    this.addresses.add(mailAddress);
  }

  public void addAddress(Address newAddress) {
    addresses.add(newAddress);
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public Address getResidentialAddress() {
    return residentialAddress;
  }

  public Address getMailingAddress() {
    return mailingAddress;
  }

  public void setResidentialAddress(Address address) {
    residentialAddress = address;
  }
}
