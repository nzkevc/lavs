package uoa.lavs.services;

import uoa.lavs.models.Customer;
import uoa.lavs.models.Phone;
import uoa.lavs.models.Phones;
import uoa.lavs.repository.PhoneRepository;
import uoa.lavs.repository.PhonesRepository;
import uoa.lavs.utils.objects.ValidationException;

class PhoneService {
  public static void createPhonesFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Phones phones = newCustomer.getPhones();
    phones.setCustomerId(newCustomer.getId());
    for (Phone phone : phones.getPhoneNumbers()) {
      phone.setCustomerID(newCustomer.getId());
      phone.validate();
      PhoneRepository.create(phone);
    }
  }

  public static void updatePhonesFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Phones phones = newCustomer.getPhones();
    for (Phone phone : phones.getPhoneNumbers()) {
      phone.validate();
      PhoneRepository.update(phone);
    }
  }

  public static Phones getPhones(Customer customer) throws RuntimeException {
    return PhonesRepository.get(customer);
  }
}
