package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;
import uoa.lavs.utils.objects.ConnectionInstance;

public class LoanPaymentsRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void getLoanNoPaymentsTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> LoanPaymentsRepository.get(loan));
  }

  // TODO: cannot currently test getLoanWithPaymentsTest as it requires a loan with payments, and we
  // cannot create a loan with payments right now
}
