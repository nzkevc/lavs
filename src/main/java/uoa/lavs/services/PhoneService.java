package uoa.lavs.services;

import uoa.lavs.models.Customer;
import uoa.lavs.models.Phones;
import uoa.lavs.repository.PhonesRepository;

class PhoneService {
  public static void createPhonesFromCustomer(Customer newCustomer) {
    // create phones
  }

  public static void updatePhonesFromCustomer(Customer newCustomer) {
    // update phones
  }

  public static Phones getPhones(Customer customer) throws RuntimeException {
    return PhonesRepository.get(customer);
  }
}
