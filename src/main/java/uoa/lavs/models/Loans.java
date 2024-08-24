package uoa.lavs.models;

import java.util.HashSet;
import java.util.Set;

public class Loans {
  private String customerId;
  private final Set<Loan> loans;

  public Loans() {
    this.loans = new HashSet<>();
  }

  public Loans(Set<Loan> loans) {
    this.loans = loans;
  }

  public Loans(String customerId) {
    this.customerId = customerId;
    this.loans = new HashSet<>();
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void addLoan(Loan newLoan) {
    if (newLoan != null) {
      loans.add(newLoan);
    }
  }

  public Set<Loan> getLoans() {
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
