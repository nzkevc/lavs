package uoa.lavs.services;

import uoa.lavs.models.Customer;
import uoa.lavs.repository.NoteRepository;

class NoteService {
  public static void createNotesFromCustomer(Customer newCustomer) {
    // create notes
  }

  public static void updateNotesFromCustomer(Customer newCustomer) {
    // update notes
  }

  public static String getNotes(Customer customer) throws RuntimeException {
    return NoteRepository.get(customer, 1);
  }
}
