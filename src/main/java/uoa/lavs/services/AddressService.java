package uoa.lavs.services;

import uoa.lavs.exceptions.ValidationException;
import uoa.lavs.models.Address;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.repository.AddressRepository;
import uoa.lavs.repository.AddressesRepository;

class AddressService {
  public static void createAddressesFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Addresses addresses = newCustomer.getAddresses();
    addresses.setCustomerId(newCustomer.getId());
    for (Address address : addresses.getAddresses()) {
      address.setCustomerId(newCustomer.getId());
      address.validate();
      AddressRepository.create(address);
    }
  }

  public static void updateAddressesFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Addresses addresses = newCustomer.getAddresses();
    for (Address address : addresses.getAddresses()) {
      address.validate();
      AddressRepository.update(address);
    }
  }

  public static Addresses getAddresses(Customer customer) throws RuntimeException {
    return AddressesRepository.get(customer);
  }
}
