package uoa.lavs.services;

import uoa.lavs.models.Customer;
import uoa.lavs.models.Employer;
import uoa.lavs.models.Employers;
import uoa.lavs.repository.EmployerRepository;
import uoa.lavs.repository.EmployersRepository;
import uoa.lavs.utils.objects.ValidationException;

class EmployerService implements IService {
  public static void createEmployersFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Employers employers = newCustomer.getEmployers();
    employers.setCustomerId(newCustomer.getId());
    for (Employer employer : employers.getEmployers()) {
      employer.setCustomerId(newCustomer.getId());
      employer.validate();
      EmployerRepository.create(employer);
    }
  }

  public static void updateEmployersFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    Employers employers = newCustomer.getEmployers();
    employers.setCustomerId(newCustomer.getId());
    for (Employer employer : employers.getEmployers()) {
      employer.validate();
      employer.setCustomerId(newCustomer.getId());
      EmployerRepository.update(employer);
    }
  }

  public static Employers getEmployers(Customer customer) throws RuntimeException {
    return EmployersRepository.get(customer);
  }
}
