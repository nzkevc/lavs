package uoa.lavs.services;

import uoa.lavs.models.*;
import uoa.lavs.repository.LoanPaymentsRepository;
import uoa.lavs.repository.LoanRepository;
import uoa.lavs.repository.LoanSummaryRepository;
import uoa.lavs.repository.LoansRepository;
import uoa.lavs.utils.objects.ValidationException;

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
    loans.setCustomerId(customerId);
    for (Loan loan : loans.getLoans()) {
      loan.validate();
      loan.setCustomerId(customerId);
      LoanRepository.create(loan);
    }
    return getLoans(new Customer(customerId));
  }

  /**
   * Standalone function for updating a loan (of which the only thing that can be updated is the
   * status).
   *
   * @param loan that should be updated in the mainframe
   * @throws ValidationException
   * @throws RuntimeException
   */
  public static Loan updateLoanStatus(Loan loan) throws ValidationException, RuntimeException {
    // TODO: also should be validateStatus
    loan.validate();
    return LoanRepository.updateStatus(loan, loan.getStatus());
  }
}
