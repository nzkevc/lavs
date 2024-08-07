package uoa.lavs.data_models;

import java.util.ArrayList;
import java.util.List;

public class Loans {
  private List<Loan> loans;

  public Loans() {
    this.loans = new ArrayList<>();
  }

  public void addLoan(Loan newLoan) {
    loans.add(newLoan);
  }

  public List<Loan> getLoans() {
    return loans;
  }

  public Loan getLoan(String id) {
    for (Loan loan : loans) {
      if (loan.getId().equals(id)) {
        return loan;
      }
    }
    return null;
  }

  public void removeLoan(String id) {
    for (Loan loan : loans) {
      if (loan.getId().equals(id)) {
        loans.remove(loan);
        return;
      }
    }
  }
}
