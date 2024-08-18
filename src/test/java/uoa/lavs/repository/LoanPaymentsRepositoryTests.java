package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;
import uoa.lavs.models.LoanPayments;
import uoa.lavs.utils.ConnectionInstance;

public class LoanPaymentsRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void getLoanPaymentsTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan = LoanRepository.create(loan);

    // Act
    LoanPayments loanPayments = new LoanPayments(loan.getLoanId(), 1);
    loanPayments = LoanPaymentsRepository.get(loanPayments);
    loan.setLoanPayments(loanPayments);

    // Assert
    assertEquals(customer.getId(), loanPayments.getCustomerId());
    assertEquals(11, loanPayments.getPayments());
  }

  @Test
  public void getLoanPaymentsTestWithInvalidLoanId() {
    // Arrange
    LoanPayments loanPayments = new LoanPayments("invalid", 1);

    // Act & Assert
    assertThrows(RuntimeException.class, () -> LoanPaymentsRepository.get(loanPayments));
  }
}
