package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Frequency;

public class LoanSummaryTests {
  @Test
  public void loanSummaryTest() {
    // Arrange
    LoanSummary loanSummary = new LoanSummary();
    loanSummary.setLoanId("123");
    loanSummary.setCustomerId("456");
    loanSummary.setCustomerName("John Doe");
    loanSummary.setPriciple(1000.0);
    loanSummary.setRateValue(0.05);
    loanSummary.setPayoffDate(LocalDate.of(2022, 1, 1));
    loanSummary.setTerm(12);
    loanSummary.setTotalInterest(50.0);
    loanSummary.setTotalLoanCost(1050.0);
    loanSummary.setPaymentAmount(87.5);
    loanSummary.setPaymentFrequency(Frequency.Monthly);

    // Act
    boolean isCorrect =
        loanSummary.getLoanId().equals("123")
            && loanSummary.getCustomerId().equals("456")
            && loanSummary.getCustomerName().equals("John Doe")
            && loanSummary.getPriciple().equals(1000.0)
            && loanSummary.getRateValue().equals(0.05)
            && loanSummary.getPayoffDate().equals(LocalDate.of(2022, 1, 1))
            && loanSummary.getTerm().equals(12)
            && loanSummary.getTotalInterest().equals(50.0)
            && loanSummary.getTotalLoanCost().equals(1050.0)
            && loanSummary.getPaymentAmount().equals(87.5)
            && loanSummary.getPaymentFrequency().equals(Frequency.Monthly);

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void loanSummaryConstructorTest() {
    // Arrange
    LoanSummary loanSummary = new LoanSummary("123");

    // Act
    boolean isCorrect = loanSummary.getLoanId().equals("123");

    // Assert
    assertTrue(isCorrect);
  }
}
