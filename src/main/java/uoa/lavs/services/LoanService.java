package uoa.lavs.services;

import uoa.lavs.models.*;
import uoa.lavs.repository.LoanPaymentsRepository;
import uoa.lavs.repository.LoanRepository;
import uoa.lavs.repository.LoanSummaryRepository;
import uoa.lavs.repository.LoansRepository;

public class LoanService implements IService {

  public static Loans getLoans(Customer customer) throws RuntimeException {
    try {
      Loans loans = LoansRepository.get(customer);
      for (Loan loan : loans.getLoans()) {
        LoanSummary loanSummary = LoanSummaryRepository.get(new LoanSummary(loan.getLoanId()));
        loan.setLoanSummary(loanSummary);

        LoanPayments loanPayments =
            LoanPaymentsRepository.get(new LoanPayments(loan.getLoanId(), 1));
        loan.setLoanPayments(loanPayments);
      }
      return loans;
    } catch (Exception e) {
      return new Loans(customer.getId());
    }
  }

  public static void createLoansFromCustomer(Customer customer) throws RuntimeException {
    Loans loans = customer.getLoans();
    for (Loan loan : loans.getLoans()) {
      LoanRepository.create(loan);
    }
  }
}
