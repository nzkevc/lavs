package uoa.lavs.services;

import uoa.lavs.models.*;
import uoa.lavs.repository.LoanRepository;
import uoa.lavs.repository.LoansRepository;

public class LoanService implements IService {

  public static Loans getLoans(Customer customer) throws RuntimeException {
    Loans loans = LoansRepository.get(customer);
    return loans;
  }

  public static Loan getLoanById(String loanId) throws RuntimeException {
    Loan loan = LoanRepository.get(loanId);
    return loan;
  }

  public static void createLoan(Loan loan) throws RuntimeException {}
}
