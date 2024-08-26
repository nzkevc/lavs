package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class LoanPaymentsTests {
  @Test
  public void loanPaymentsTest() {
    // Arrange
    LoanPayments loanPayments = new LoanPayments("123");

    // Act
    boolean isCorrect = loanPayments.getLoanId().equals("123");

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void gettersAndSettersTest() {
    // Arrange
    LoanPayments loanPayments = new LoanPayments("123");
    loanPayments.setLoanId("456");
    loanPayments.setCustomerId("123");
    loanPayments.setCustomerName("John Doe");
    loanPayments.setPayments(0);
    loanPayments.setPages(20);
    loanPayments.setPaymentDates(new ArrayList<>());
    loanPayments.setPaymentInterests(new ArrayList<>());
    loanPayments.setPaymentPrincipals(new ArrayList<>());
    loanPayments.setPaymentRemainings(new ArrayList<>());
    loanPayments.setPaymentNumbers(new ArrayList<>());

    // Act
    boolean isCorrect =
        loanPayments.getLoanId().equals("456")
            && loanPayments.getCustomerId().equals("123")
            && loanPayments.getCustomerName().equals("John Doe")
            && loanPayments.getPayments().equals(0)
            && loanPayments.getPages().equals(20)
            && loanPayments.getPaymentDates().size() == 0
            && loanPayments.getPaymentInterests().size() == 0
            && loanPayments.getPaymentPrincipals().size() == 0
            && loanPayments.getPaymentRemainings().size() == 0
            && loanPayments.getPaymentNumbers().size() == 0;

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void addingLoanPaymentsTest() {
    // Arrange
    LoanPayments loanPayments = new LoanPayments("123");
    loanPayments.addPayment(LocalDate.of(2001, 01, 01), 1, 100.0, 10.0, 90.0);

    // Act
    boolean isCorrect =
        loanPayments.getPaymentDates().get(0).equals(LocalDate.of(2001, 01, 01))
            && loanPayments.getPaymentInterests().get(0).equals(100.0)
            && loanPayments.getPaymentPrincipals().get(0).equals(10.0)
            && loanPayments.getPaymentRemainings().get(0).equals(90.0)
            && loanPayments.getPaymentNumbers().get(0).equals(1);

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void addingMultiplePayments() {
    // Arrange
    LoanPayments loanPayments = new LoanPayments("123");

    // Act
    loanPayments.addPayment(LocalDate.of(2001, 01, 01), 1, 100.0, 10.0, 90.0);
    loanPayments.addPayment(LocalDate.of(2001, 02, 01), 2, 90.0, 10.0, 80.0);
    loanPayments.addPayment(LocalDate.of(2001, 03, 01), 3, 80.0, 10.0, 70.0);

    // Assert
    assertEquals(3, loanPayments.getPayments());
  }

  @Test
  public void addingPaymentThroughIndividualMethods() {
    // Arrange
    LoanPayments loanPayments = new LoanPayments("123");

    // Act
    loanPayments.addPaymentInterest(100.0);
    loanPayments.addPaymentPrincipal(10.0);
    loanPayments.addPaymentRemaining(90.0);
    loanPayments.addPaymentNumber(1);

    // Assert
    assertEquals(1, loanPayments.getPayments());
  }
}
