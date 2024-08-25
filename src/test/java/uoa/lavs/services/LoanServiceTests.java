package uoa.lavs.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;
import uoa.lavs.repository.CustomerRepository;
import uoa.lavs.repository.LoansRepository;
import uoa.lavs.utils.objects.ConnectionInstance;

public class LoanServiceTests {
  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void createLoansTest() {
    // Arrange
    LoanService loanService = new LoanService();
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);
    customer.getLoans().addLoan(loan);

    // Act
    LoanService.createLoansByCustomerId(customer.getId(), customer.getLoans());
    customer.setLoans(LoansRepository.get(customer));

    // Assert
    assertEquals(customer.getId(), customer.getLoans().getCustomerId());
    assertEquals(1, customer.getLoans().getLoans().size());
  }

  @Test
  public void createMultipleLoansTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);
    Loan loan2 = TestEntityCreator.createBasicLoan(customer);
    customer.getLoans().addLoan(loan);
    customer.getLoans().addLoan(loan2);

    // Act
    LoanService.createLoansByCustomerId(customer.getId(), customer.getLoans());
    customer.setLoans(LoansRepository.get(customer));

    // Assert
    assertEquals(customer.getId(), customer.getLoans().getCustomerId());
    assertEquals(2, customer.getLoans().getLoans().size());
  }

  @Test
  public void updateLoanStatusTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);

    customer.getLoans().addLoan(loan);

    // Act
    LoanService.createLoansByCustomerId(customer.getId(), customer.getLoans());
    customer.setLoans(LoansRepository.get(customer));
    for (Loan l : customer.getLoans().getLoans()) {
      loan = LoanService.updateLoanStatus(l);
    }

    // Assert
    assertNotEquals(null, loan.getStatus());
  }

  @Test
  public void getLoansTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);
    customer.getLoans().addLoan(loan);

    LoanService.createLoansByCustomerId(customer.getId(), customer.getLoans());
    customer.setLoans(LoansRepository.get(customer));

    // Act
    LoansRepository.get(customer);

    // Assert
    assertNotEquals(null, customer.getLoans());
  }

  @Test
  public void getLoansWithNoLoansTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    // Act
    LoansRepository.get(customer);

    // Assert
    assertNotEquals(null, customer.getLoans());
  }

  @Test
  public void getMultipleLoansTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan = TestEntityCreator.createBasicLoan(customer);
    Loan loan2 = TestEntityCreator.createBasicLoan(customer);
    customer.getLoans().addLoan(loan);
    customer.getLoans().addLoan(loan2);

    LoanService.createLoansByCustomerId(customer.getId(), customer.getLoans());
    customer.setLoans(LoansRepository.get(customer));

    // Act
    LoansRepository.get(customer);

    // Assert
    assertNotEquals(null, customer.getLoans());
  }
}
