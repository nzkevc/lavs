package uoa.lavs.services;

import uoa.lavs.exceptions.ValidationException;
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

  /**
   * Creates a loan in the mainframe given the customerId of an existing customer and the loans to
   * be created. Loans should already reference the customer's existing customerId.
   *
   * @param customerId
   * @param loans
   * @return the loans created in the mainframe, to be attached back to the customer in controller
   *     layer (as it contains the newly created loanSummary and loanPayments)
   * @throws ValidationException
   * @throws RuntimeException
   */
  public static Loans createLoansByCustomerId(String customerId, Loans loans)
      throws ValidationException, RuntimeException {
    for (Loan loan : loans.getLoans()) {
      loan.validate();
      LoanRepository.create(loan);
    }
    return getLoans(new Customer(customerId));
  }

  // TODO: would be validateStatus rather than validating all loan fields
  public static void updateLoansFromCustomer(Customer customer)
      throws ValidationException, RuntimeException {
    Loans loans = customer.getLoans();
    for (Loan loan : loans.getLoans()) {
      loan.validate();
      LoanRepository.updateStatus(loan, loan.getStatus());
    }
  }

  /**
   * Standalone function for updating a loan (of which the only thing that can be updated is the
   * status).
   *
   * @param loan that should be updated in the mainframe
   * @throws ValidationException
   * @throws RuntimeException
   */
  public static void updateLoanStatus(Loan loan) throws ValidationException, RuntimeException {
    // TODO: also should be validateStatus
    loan.validate();
    LoanRepository.updateStatus(loan, loan.getStatus());
  }
}
