package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.loan.LoadLoanSummary;
import uoa.lavs.models.LoanSummary;
import uoa.lavs.utils.ConnectionInstance;

public class LoanSummaryRepository {
  /** Retrieves loan summary from database */
  public static LoanSummary get(LoanSummary loanSummary) {
    Connection connection = ConnectionInstance.getConnection();

    LoadLoanSummary message = new LoadLoanSummary();
    message.setLoanId(loanSummary.getLoanId());

    Status status = message.send(connection);
    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get loan summary: " + status.getErrorMessage());
    }

    loanSummary.setCustomerId(message.getCustomerIdFromServer());
    loanSummary.setCustomerName(message.getCustomerNameFromServer());
    loanSummary.setPriciple(message.getPrincipalFromServer());
    loanSummary.setRateValue(message.getRateValueFromServer());
    loanSummary.setPayoffDate(message.getPayoffDateFromServer());
    loanSummary.setTerm(message.getTermFromServer());
    loanSummary.setTotalInterest(message.getTotalInterestFromServer());
    loanSummary.setTotalLoanCost(message.getTotalLoanCostFromServer());
    loanSummary.setPaymentAmount(message.getPaymentAmountFromServer());
    loanSummary.setPaymentFrequency(message.getPaymentFrequencyFromServer());

    return loanSummary;
  }
}
