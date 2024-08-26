package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LoanPaymentRowTests {

  @Test
  public void testLoanPaymentRow() {
    LoanPaymentRow loanPaymentRow = new LoanPaymentRow(1.0, 2.0, 3.0, 4);
    assertTrue(loanPaymentRow.getPaymentInterest() == 1.0);
    assertTrue(loanPaymentRow.getPaymentPrincipal() == 2.0);
    assertTrue(loanPaymentRow.getPaymentRemaining() == 3.0);
    assertTrue(loanPaymentRow.getPaymentNumber() == 4);
  }
}
