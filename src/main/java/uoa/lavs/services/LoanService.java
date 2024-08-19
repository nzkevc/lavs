package uoa.lavs.services;

import uoa.lavs.models.*;
import uoa.lavs.repository.LoanRepository;
import uoa.lavs.repository.LoansRepository;

public class LoanService implements IService {

  public static Loans getLoans(Customer customer) throws RuntimeException {
    try {
      Loans loans = LoansRepository.get(customer);
      return loans;
    } catch (Exception e) {
      return new Loans(customer.getId());
    }
  }

  public static Loan getLoanById(String loanId) throws RuntimeException {
    Loan loan = LoanRepository.get(loanId);
    return loan;
  }

  public static void createLoansFromCustomer(Customer customer) throws RuntimeException {
    Loans loans = customer.getLoans();
    for (Loan loan : loans.getLoans()) {
      LoanRepository.create(loan);
    }
  }
}
