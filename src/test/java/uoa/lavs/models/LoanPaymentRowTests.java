package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class LoanPaymentRowTests {

  @Test
  public void testLoanPaymentRow() {
    LoanPaymentRow loanPaymentRow = new LoanPaymentRow(1.0, 2.0, 3.0, 4, LocalDate.of(1, 1, 1));
    assertTrue(loanPaymentRow.getPaymentInterest() == 1.0);
    assertTrue(loanPaymentRow.getPaymentPrincipal() == 2.0);
    assertTrue(loanPaymentRow.getPaymentRemaining() == 3.0);
    assertTrue(loanPaymentRow.getPaymentNumber() == 4);
    assertTrue(loanPaymentRow.getPaymentDate().equals(LocalDate.of(1, 1, 1)));
  }
}
