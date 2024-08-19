package uoa.lavs.services;

import org.dizitart.no2.exceptions.ValidationException;
import uoa.lavs.models.Address;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.repository.AddressRepository;
import uoa.lavs.repository.AddressesRepository;

class AddressService {
  public static void createAddressesFromCustomer(Customer newCustomer) {
    // create addresses
  }

  public static void updateAddressesFromCustomer(Customer newCustomer) throws ValidationException {
    Addresses addresses = newCustomer.getAddresses();
    for (Address address : addresses.getAddresses()) {
      address.validate();
      AddressRepository.create(address);
    }
  }

  public static Addresses getAddresses(Customer customer) throws RuntimeException {
    return AddressesRepository.get(customer);
  }
}
