package uoa.lavs.models;

import java.util.ArrayList;
import java.util.List;

public class Loans {
  private String customerId;
  private List<Loan> loans;

  public Loans(String customerId) {
    this.customerId = customerId;
    this.loans = new ArrayList<>();
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void addLoan(Loan newLoan) {
    loans.add(newLoan);
  }

  public List<Loan> getLoans() {
    return loans;
  }

  public int getLoanCount() {
    return loans.size();
  }

  public Loan getLoan(String id) {
    for (Loan loan : loans) {
      if (loan.getLoanId().equals(id)) {
        return loan;
      }
    }
    return null;
  }

  public void removeLoan(String id) {
    for (Loan loan : loans) {
      if (loan.getLoanId().equals(id)) {
        loans.remove(loan);
        return;
      }
    }
  }
}
