package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.loan.LoadLoanPayments;
import uoa.lavs.models.LoanPayments;
import uoa.lavs.utils.objects.ConnectionInstance;

public class LoanPaymentsRepository {
  /** Retrieves loan payments from database */
  public static LoanPayments get(LoanPayments loanPayments) {
    Connection connection = ConnectionInstance.getConnection();

    LoadLoanPayments message = new LoadLoanPayments();
    message.setLoanId(loanPayments.getLoanId());

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get loan payments: " + status.getErrorMessage());
    }

    loanPayments.setCustomerId(message.getCustomerIdFromServer());
    loanPayments.setCustomerName(message.getCustomerNameFromServer());
    loanPayments.setPayments(message.getPaymentCountFromServer());

    int pages = message.getPageCountFromServer();

    for (int i = 1; i <= pages; i++) {
      message.setNumber(i);
      status = message.send(connection);

      if (!status.getWasSuccessful()) {
        throw new RuntimeException("Failed to get loan payments: " + status.getErrorMessage());
      }

      for (int j = 1; j <= message.getPaymentCountFromServer(); j++) {
        loanPayments.getPaymentDates().add(message.getPaymentDateFromServer(j));
        loanPayments.getPaymentInterests().add(message.getPaymentInterestFromServer(i));
        loanPayments.getPaymentPrincipals().add(message.getPaymentPrincipalFromServer(i));
        loanPayments.getPaymentRemainings().add(message.getPaymentRemainingFromServer(i));
        loanPayments.getPaymentNumbers().add(message.getPaymentNumberFromServer(i));
      }
    }

    return loanPayments;
  }
}
