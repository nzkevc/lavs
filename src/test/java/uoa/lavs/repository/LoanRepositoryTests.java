package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;
import uoa.lavs.utils.objects.ConnectionInstance;

public class LoanRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createLoanTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);

    // Act
    loan = LoanRepository.create(loan);

    // Assert
    assertNotEquals(null, loan.getLoanId());
  }

  @Test
  public void createLoanNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    Loan loan = TestEntityCreator.createBasicLoan(customer);

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> LoanRepository.create(loan));
  }

  @Test
  public void getLoanTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan = LoanRepository.create(loan);

    // Act
    loan.setCustomerName("New Name");
    loan = LoanRepository.get(loan.getLoanId());

    // Assert
    assertEquals("John Doe", loan.getCustomerName());
  }

  @Test
  public void getLoanNoLoanTest() {
    // Arrange

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> LoanRepository.get("noloan"));
  }

  @Test
  public void updateLoanStatusTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan = LoanRepository.create(loan);

    // Act
    loan.setStatus(LoanStatus.Cancelled);
    loan = LoanRepository.updateStatus(loan, LoanStatus.Pending);

    // Assert
    assertEquals(LoanStatus.Pending, loan.getStatus());
  }

  @Test
  public void updateLoanStatusNoLoanTest() {
    // Arrange
    Loan loan = new Loan("noloan");

    // Act

    // Assert
    assertThrows(
        RuntimeException.class, () -> LoanRepository.updateStatus(loan, LoanStatus.Pending));
  }
}
