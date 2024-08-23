package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.models.Loan;
import uoa.lavs.models.Loans;
import uoa.lavs.utils.objects.ConnectionInstance;

public class LoansRepositoryTests {

  @BeforeAll
  public static void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void getLoansTest() {
    // Arrange
    LoansRepository loansRepository = new LoansRepository();
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    Loan loan1 = TestEntityCreator.createBasicLoan(customer);
    Loan loan2 = TestEntityCreator.createBasicSecondaryLoan(customer);

    loan1 = LoanRepository.create(loan1);
    loan2 = LoanRepository.create(loan2);

    // Act
    Loans loans = LoansRepository.get(customer);
    Loan fetchedLoan1 = loans.getLoan(loan1.getLoanId());
    Loan fetchedLoan2 = loans.getLoan(loan2.getLoanId());

    // Assert
    assertEquals(2, loans.getLoanCount());
    assertEquals(loan1.getLoanId(), fetchedLoan1.getLoanId());
    assertEquals(loan2.getLoanId(), fetchedLoan2.getLoanId());
    assertNotEquals(fetchedLoan1, fetchedLoan2);
  }

  @Test
  public void getLoansNoCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("nocustomer");

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> LoansRepository.get(customer));
  }
}
