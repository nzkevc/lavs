package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.RateType;

public class LoanTests {
  @Test
  public void constuctorTest() {
    // Arrange
    LocalDate date = LocalDate.of(2021, 1, 1);
    Loan loan =
        new Loan(
            "123",
            "123",
            "John Doe",
            1000,
            date,
            12,
            12,
            0.05,
            RateType.Fixed,
            Frequency.Monthly,
            100,
            Frequency.Monthly,
            false);

    // Act
    boolean isCorrect =
        loan.getCustomerId().equals("123")
            && loan.getLoanId().equals("123")
            && loan.getCustomerName().equals("John Doe")
            && loan.getPrincipleCents() == 1000
            && loan.getStartDate().equals(date)
            && loan.getPeriodMonths() == 12
            && loan.getTerm() == 12
            && loan.getInterestRate() == 0.05
            && loan.getRateType() == RateType.Fixed
            && loan.getCompoundingFrequency() == Frequency.Monthly
            && loan.getPaymentAmountCents() == 100
            && loan.getPaymentFrequency() == Frequency.Monthly
            && !loan.isInterestOnly();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void constuctor2Test() {
    // Arrange
    LocalDate date = LocalDate.of(2021, 1, 1);
    Loan loan = new Loan("123");
    loan.setCustomerId("123");
    loan.setCustomerName("John Doe");
    loan.setPrincipleCents(1000);
    loan.setStartDate(date);
    loan.setPeriodMonths(12);
    loan.setTerm(12);
    loan.setInterestRate(0.05);
    loan.setRateType(RateType.Fixed);
    loan.setCompoundingFrequency(Frequency.Monthly);
    loan.setPaymentAmountCents(0);
    loan.setPaymentFrequency(Frequency.Monthly);
    loan.setInterestOnly(true);

    // Act
    boolean isCorrect =
        loan.getCustomerId().equals("123")
            && loan.getLoanId().equals("123")
            && loan.getCustomerName().equals("John Doe")
            && loan.getPrincipleCents() == 1000
            && loan.getStartDate().equals(date)
            && loan.getPeriodMonths() == 12
            && loan.getTerm() == 12
            && loan.getInterestRate() == 0.05
            && loan.getRateType() == RateType.Fixed
            && loan.getCompoundingFrequency() == Frequency.Monthly
            && loan.getPaymentAmountCents() == 0
            && loan.getPaymentFrequency() == Frequency.Monthly
            && loan.isInterestOnly();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void emptyConstructorTest() {
    // Arrange
    Loan loan = new Loan();
    loan.setCustomerId("123");
    loan.setLoanId("123");
    loan.setCustomerName("John Doe");
    loan.setPrincipleCents(1000);
    loan.setStartDate(LocalDate.of(2021, 1, 1));
    loan.setPeriodMonths(12);
    loan.setTerm(12);
    loan.setInterestRate(0.05);
    loan.setRateType(RateType.Fixed);
    loan.setCompoundingFrequency(Frequency.Monthly);
    loan.setPaymentAmountCents(100);
    loan.setPaymentFrequency(Frequency.Monthly);
    loan.setInterestOnly(false);

    // Act
    boolean isCorrect =
        loan.getCustomerId().equals("123")
            && loan.getLoanId().equals("123")
            && loan.getCustomerName().equals("John Doe")
            && loan.getPrincipleCents() == 1000
            && loan.getStartDate().equals(LocalDate.of(2021, 1, 1))
            && loan.getPeriodMonths() == 12
            && loan.getTerm() == 12
            && loan.getInterestRate() == 0.05
            && loan.getRateType() == RateType.Fixed
            && loan.getCompoundingFrequency() == Frequency.Monthly
            && loan.getPaymentAmountCents() == 100
            && loan.getPaymentFrequency() == Frequency.Monthly
            && !loan.isInterestOnly();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void loanEqualsTest() {
    // Arrange
    Loan loan1 = new Loan("123");
    loan1.setCustomerId("123");
    loan1.setLoanId("123");
    loan1.setCustomerName("John Doe");
    loan1.setPrincipleCents(1000);
    loan1.setStartDate(LocalDate.of(2021, 1, 1));
    loan1.setPeriodMonths(12);
    loan1.setTerm(12);
    loan1.setInterestRate(0.05);
    loan1.setRateType(RateType.Fixed);
    loan1.setCompoundingFrequency(Frequency.Monthly);
    loan1.setPaymentAmountCents(100);
    loan1.setPaymentFrequency(Frequency.Monthly);
    loan1.setInterestOnly(false);

    Loan loan2 = new Loan("123");
    loan2.setCustomerId("123");
    loan2.setLoanId("123");
    loan2.setCustomerName("John Doe");
    loan2.setPrincipleCents(10);
    loan2.setStartDate(LocalDate.of(2020, 1, 1));
    loan2.setPeriodMonths(10);
    loan2.setTerm(10);
    loan2.setInterestRate(0.10);
    loan2.setRateType(RateType.Fixed);
    loan2.setCompoundingFrequency(Frequency.Monthly);
    loan2.setPaymentAmountCents(50);
    loan2.setPaymentFrequency(Frequency.Monthly);
    loan2.setInterestOnly(false);

    // Act

    // Assert
    assertTrue(loan1.equals(loan2));
    assertTrue(loan2.equals(loan1));
  }

  @Test
  public void loanNotEqualNullTest() {
    // Arrange
    Loan loan = new Loan("123");

    // Act

    // Assert
    assertFalse(loan.equals(null));
  }

  @Test
  public void loanNotEqualDifferentClassTest() {
    // Arrange
    Loan loan1 = new Loan("123");
    String loan2 = "123";

    // Act

    // Assert
    assertFalse(loan1.equals(loan2));
  }

  @Test
  public void getLoanPaymentsTest() {
    // Arrange
    Loan loan = new Loan("123");
    loan.setCustomerId("123");
    loan.setLoanId("123");
    loan.setCustomerName("John Doe");
    loan.setPrincipleCents(1000);
    loan.setStartDate(LocalDate.of(2021, 1, 1));
    loan.setPeriodMonths(12);
    loan.setTerm(12);
    loan.setInterestRate(0.05);
    loan.setRateType(RateType.Fixed);
    loan.setCompoundingFrequency(Frequency.Monthly);
    loan.setPaymentAmountCents(100);
    loan.setPaymentFrequency(Frequency.Monthly);
    loan.setInterestOnly(false);

    // Act

    // Assert
    // Null because has not been set by the mainframe repository connection here
    assertEquals(null, loan.getLoanPayments());
  }

  @Test
  public void getLoanSummaryTest() {
    // Arrange
    Loan loan = new Loan("123");
    loan.setCustomerId("123");
    loan.setLoanId("123");
    loan.setCustomerName("John Doe");
    loan.setPrincipleCents(1000);
    loan.setStartDate(LocalDate.of(2021, 1, 1));
    loan.setPeriodMonths(12);
    loan.setTerm(12);
    loan.setInterestRate(0.05);
    loan.setRateType(RateType.Fixed);
    loan.setCompoundingFrequency(Frequency.Monthly);
    loan.setPaymentAmountCents(100);
    loan.setPaymentFrequency(Frequency.Monthly);
    loan.setInterestOnly(false);

    // Act

    // Assert
    // Null because has not been set by the mainframe repository connection here
    assertEquals(null, loan.getLoanSummary());
  }

  @Test
  public void validateTest() {
    // Arrange
    Loan loan = new Loan("123");
    loan.setCustomerId("123");
    loan.setLoanId("123");
    loan.setCustomerName("John Doe");
    loan.setPrincipleCents(1000);
    loan.setStartDate(LocalDate.of(2021, 1, 1));
    loan.setPeriodMonths(12);
    loan.setTerm(12);
    loan.setInterestRate(0.05);
    loan.setRateType(RateType.Fixed);
    loan.setCompoundingFrequency(Frequency.Monthly);
    loan.setPaymentAmountCents(100);
    loan.setPaymentFrequency(Frequency.Monthly);
    loan.setInterestOnly(false);

    // Act
    boolean isValid = loan.validate();

    // Assert
    assertTrue(isValid);
  }
}
