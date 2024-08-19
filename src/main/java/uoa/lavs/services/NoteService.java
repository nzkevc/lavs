package uoa.lavs.services;

import uoa.lavs.exceptions.ValidationException;
import uoa.lavs.models.Customer;
import uoa.lavs.repository.NoteRepository;

class NoteService {
  public static void createNotesFromCustomer(Customer newCustomer) {
    // create notes
  }

  public static void updateNotesFromCustomer(Customer newCustomer)
      throws ValidationException, RuntimeException {
    String notes = newCustomer.getNotes();
    NoteRepository.update(notes, newCustomer);
  }

  public static String getNotes(Customer customer) throws RuntimeException {
    return NoteRepository.get(customer, 1);
  }
}
