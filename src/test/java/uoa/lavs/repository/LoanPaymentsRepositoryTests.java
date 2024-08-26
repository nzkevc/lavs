package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;
import uoa.lavs.models.LoanPayments;
import uoa.lavs.utils.objects.ConnectionInstance;

public class LoanPaymentsRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void getLoanPaymentsTest() {
    // Arrange
    LoanPaymentsRepository loanPaymentsRepository = new LoanPaymentsRepository();
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan = LoanRepository.create(loan);

    // Act
    LoanPayments loanPayments = new LoanPayments(loan.getLoanId());
    loanPayments = LoanPaymentsRepository.get(loanPayments);
    loan.setLoanPayments(loanPayments);

    // Assert
    assertEquals(customer.getId(), loanPayments.getCustomerId());
    assertEquals(17, loanPayments.getPayments());
    assertEquals(LocalDate.of(2025, 12, 22), loanPayments.getPaymentDates().get(16));
    assertEquals(449707.25, loanPayments.getPaymentRemainings().get(0));
    assertEquals(2, loanPayments.getPaymentNumbers().get(1));
    assertEquals(326.24, loanPayments.getPaymentPrincipals().get(16));
    assertEquals(
        loanPayments.getPayments(),
        loanPayments.getPaymentNumbers().get(loanPayments.getPayments() - 1));
  }
}
