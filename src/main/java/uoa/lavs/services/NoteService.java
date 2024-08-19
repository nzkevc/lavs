package uoa.lavs.services;

import uoa.lavs.models.Customer;
import uoa.lavs.repository.NoteRepository;

class NoteService {
  public static void updateNotesFromCustomer(Customer newCustomer) throws RuntimeException {
    String notes = newCustomer.getNotes();
    if (notes == null) {
      notes = "";
    }
    NoteRepository.update(notes, newCustomer);
  }

  public static String getNotes(Customer customer) throws RuntimeException {
    return NoteRepository.get(customer, 1);
  }
}
