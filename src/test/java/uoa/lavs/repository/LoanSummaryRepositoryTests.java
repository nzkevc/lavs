package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;
import uoa.lavs.models.LoanSummary;
import uoa.lavs.utils.objects.ConnectionInstance;

public class LoanSummaryRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void getLoanSummaryTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan = LoanRepository.create(loan);

    // Act
    LoanSummary loanSummary = new LoanSummary(loan.getLoanId());
    loanSummary = LoanSummaryRepository.get(loanSummary);
    loan.setLoanSummary(loanSummary);

    // Assert
    assertEquals(customer.getId(), loanSummary.getCustomerId());
  }

  @Test
  public void getLoanSummaryFailTest() {
    // Arrange
    LoanSummary loanSummary = new LoanSummary("invalid");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> LoanSummaryRepository.get(loanSummary));
  }
}
