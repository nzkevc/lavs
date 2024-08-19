package uoa.lavs.services;

import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.repository.AddressesRepository;

class AddressService {
  public static void createAddressesFromCustomer(Customer newCustomer) {
    // create addresses
  }

  public static void updateAddressesFromCustomer(Customer newCustomer) {
    // update addresses
  }

  public static Addresses getAddresses(Customer customer) throws RuntimeException {
    return AddressesRepository.get(customer);
  }
}
