package uoa.lavs.services;

import uoa.lavs.models.Address;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.repository.AddressRepository;
import uoa.lavs.repository.AddressesRepository;
import uoa.lavs.utils.objects.ValidationException;

class AddressService implements IService {
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
    addresses.setCustomerId(newCustomer.getId());
    for (Address address : addresses.getAddresses()) {
      address.validate();
      address.setCustomerId(newCustomer.getId());
      AddressRepository.update(address);
    }
  }

  public static Addresses getAddresses(Customer customer) throws RuntimeException {
    return AddressesRepository.get(customer);
  }
}
