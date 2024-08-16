package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.loan.LoadLoanPayments;
import uoa.lavs.models.Loan;
import uoa.lavs.utils.ConnectionInstance;

public class LoanPaymentsRepository {
  /** Retrieves loan payments from database */
  public static Loan get(Loan loan) {
    Connection connection = ConnectionInstance.getConnection();

    LoadLoanPayments message = new LoadLoanPayments();
    message.setLoanId(loan.getLoanId());

    // Currently cannot add payments to the database, so can only test for exception
    Status status = message.send(connection);
    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get loan payments: " + status.getErrorMessage());
    }

    int count = message.getPaymentCountFromServer();

    for (int i = 1; i <= count; i++) {
      loan.addPayment(
          message.getPaymentNumberFromServer(i),
          message.getPaymentInterestFromServer(i),
          message.getPaymentPrincipalFromServer(i),
          message.getPaymentRemainingFromServer(i));
    }

    return loan;
  }
}
