package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;

public class LoansTests {
  @Test
  public void constructorTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("123");
    Loans loans = new Loans(customer.getId());
    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan.setLoanId("1234");

    // Act
    loans.addLoan(loan);
    boolean isCorrect = loans.getLoans().size() == 1;

    // Assert
    assertTrue(isCorrect);
    assertEquals(customer.getId(), loans.getCustomerId());
  }

  @Test
  public void emptyConstructorTest() {
    // Arrange
    Loans loans = new Loans();
    loans.setCustomerId("123");

    // Act
    boolean isCorrect = loans.getLoans().isEmpty();

    // Assert
    assertTrue(isCorrect);
    assertEquals("123", loans.getCustomerId());
  }

  @Test
  public void loanSetConstructorTest() {
    // Arrange
    Loan loan1 = new Loan("123");
    loan1.setCustomerId("123");
    loan1.setLoanId("123");

    Set<Loan> loanSet = new HashSet<>();
    loanSet.add(loan1);

    Loans loans = new Loans(loanSet);

    // Act
    // Assert
    assertTrue(loans.getLoans().contains(loan1));
  }

  @Test
  public void getLoanCountTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("123");
    Loans loans = new Loans(customer.getId());
    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan.setLoanId("1234");

    // Act
    loans.addLoan(loan);

    // Assert
    assertEquals(1, loans.getLoanCount());
  }

  @Test
  public void getSpecificLoanTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("123");
    Loans loans = new Loans(customer.getId());
    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan.setLoanId("1234");
    Loan loan2 = TestEntityCreator.createBasicLoan(customer);
    loan2.setLoanId("12345");
    Loan loan3 = TestEntityCreator.createBasicLoan(customer);
    loan3.setLoanId("123456");

    // Act
    loans.addLoan(loan);
    loans.addLoan(loan2);
    loans.addLoan(loan3);

    // Assert
    assertEquals(loan2, loans.getLoan("12345"));
  }

  @Test
  public void removeLoanTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("123");
    Loans loans = new Loans(customer.getId());
    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan.setLoanId("1234");
    Loan loan2 = TestEntityCreator.createBasicLoan(customer);
    loan2.setLoanId("12345");
    Loan loan3 = TestEntityCreator.createBasicLoan(customer);
    loan3.setLoanId("123456");

    // Act
    loans.addLoan(loan);
    loans.addLoan(loan2);
    loans.addLoan(loan3);
    loans.removeLoan("12345");

    // Assert
    assertEquals(2, loans.getLoanCount());
    assertNull(loans.getLoan("12345"));
  }

  @Test
  public void getLoansTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("123");
    Loans loans = new Loans(customer.getId());
    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan.setLoanId("1234");
    Loan loan2 = TestEntityCreator.createBasicLoan(customer);
    loan2.setLoanId("12345");
    Loan loan3 = TestEntityCreator.createBasicLoan(customer);
    loan3.setLoanId("123456");

    // Act
    loans.addLoan(loan);
    loans.addLoan(loan2);
    loans.addLoan(loan3);

    // Assert
    assertEquals(3, loans.getLoans().size());
  }

  @Test
  public void removeNonexistantLoanTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("123");
    Loans loans = new Loans(customer.getId());
    Loan loan = TestEntityCreator.createBasicLoan(customer);
    loan.setLoanId("1234");
    Loan loan2 = TestEntityCreator.createBasicLoan(customer);
    loan2.setLoanId("12345");
    Loan loan3 = TestEntityCreator.createBasicLoan(customer);
    loan3.setLoanId("123456");

    // Act
    loans.addLoan(loan);
    loans.addLoan(loan2);
    loans.addLoan(loan3);
    loans.removeLoan("1234567");

    // Assert
    assertEquals(3, loans.getLoanCount());
  }

  @Test
  public void addingNullLoanTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer.setId("123");
    Loans loans = new Loans(customer.getId());

    // Act
    loans.addLoan(null);

    // Assert
    assertEquals(0, loans.getLoanCount());
  }
}
