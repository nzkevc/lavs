package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.loan.FindLoan;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;
import uoa.lavs.models.Loans;
import uoa.lavs.utils.ConnectionInstance;

public class LoansRepository {
  /** Retrieves loans from database */
  public static Loans get(Customer customer) {
    Connection connection = ConnectionInstance.getConnection();

    FindLoan message = new FindLoan();
    message.setId(customer.getId());

    Status status = message.send(connection);
    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get loans: " + status.getErrorMessage());
    }

    Loans loans = new Loans(customer.getId());
    int count = message.getCountFromServer();

    for (int i = 1; i <= count; i++) {
      Loan loan = LoanRepository.get(message.getLoanIdFromServer(i));

      loans.addLoan(loan);
    }

    return loans;
  }
}
