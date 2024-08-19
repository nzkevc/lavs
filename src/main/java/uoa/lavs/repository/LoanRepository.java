package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.loan.LoadLoan;
import uoa.lavs.mainframe.messages.loan.UpdateLoan;
import uoa.lavs.mainframe.messages.loan.UpdateLoanStatus;
import uoa.lavs.models.Loan;
import uoa.lavs.utils.objects.ConnectionInstance;

public class LoanRepository {
  /** Creates new instance of a loan in database and retrieves identifying number */
  public static Loan create(Loan loan) {
    Connection connection = ConnectionInstance.getConnection();

    loan.setLoanId(null);
    UpdateLoan message = new UpdateLoan();

    message.setCustomerId(loan.getCustomerId());
    message.setPrincipal(loan.getPrincipleCents());
    message.setRateValue(loan.getInterestRate());
    message.setRateType(loan.getRateType());
    message.setStartDate(loan.getStartDate());
    message.setPeriod(loan.getPeriodMonths());
    message.setTerm(loan.getTerm());
    message.setPaymentAmount(loan.getPaymentAmountCents());
    message.setPaymentFrequency(loan.getPaymentFrequency());
    message.setCompounding(loan.getCompoundingFrequency());

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to create loan: " + status.getErrorMessage());
    } else {
      loan.setLoanId(message.getLoanIdFromServer());
      return loan;
    }
  }

  /** Updates existing loan in database */
  public static Loan get(String loanId) {
    Loan loan = new Loan(loanId);

    Connection connection = ConnectionInstance.getConnection();
    LoadLoan message = new LoadLoan();

    message.setLoanId(loanId);
    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get loan: " + status.getErrorMessage());
    } else {
      loan.setCustomerId(message.getCustomerIdFromServer());
      loan.setCustomerName(message.getCustomerNameFromServer());
      loan.setStatus(message.getStatusFromServer());
      loan.setPrincipleCents(message.getPrincipalFromServer());
      loan.setInterestRate(message.getRateValueFromServer());
      loan.setRateType(message.getRateTypeFromServer());
      loan.setStartDate(message.getStartDateFromServer());
      loan.setPeriodMonths(message.getPeriodFromServer());
      loan.setTerm(message.getTermFromServer());
      loan.setPaymentAmountCents(message.getPaymentAmountFromServer());
      loan.setPaymentFrequency(message.getPaymentFrequencyFromServer());
      loan.setCompoundingFrequency(message.getCompoundingFromServer());

      return loan;
    }
  }

  public static Loan updateStatus(Loan loan, LoanStatus loanStatus) {
    Connection connection = ConnectionInstance.getConnection();
    UpdateLoanStatus message = new UpdateLoanStatus();

    message.setLoanId(loan.getLoanId());
    message.setStatus(loanStatus);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to update loan status: " + status.getErrorMessage());
    }

    loan.setStatus(message.getStatusFromServer());
    return loan;
  }
}
