package uoa.lavs.models;

import java.time.LocalDate;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.mainframe.messages.loan.FindLoan;
import uoa.lavs.mainframe.messages.loan.LoadLoan;
import uoa.lavs.mainframe.messages.loan.UpdateLoan;

public class Loan implements IModel<Loan> {

  private String customerId;
  private String loanId;
  private LoanStatus status;
  private double principleCents;
  private LocalDate startDate;
  private int periodMonths;
  private int term;
  private double interestRate;
  private RateType rateType;
  private Frequency compoundingFrequency;
  private double paymentAmountCents;
  private Frequency paymentFrequency;
  private boolean interestOnly;

  public Loan(
      String customerId,
      String loanId,
      double principleCents,
      LocalDate startDate,
      int periodMonths,
      int term,
      double interestRate,
      RateType rateType,
      Frequency compoundingFrequency,
      double paymentAmountCents,
      Frequency paymentFrequency,
      boolean interestOnly) {
    this.customerId = customerId;
    this.loanId = loanId;
    this.principleCents = principleCents;
    this.startDate = startDate;
    this.periodMonths = periodMonths;
    this.term = term;
    this.interestRate = interestRate;
    this.rateType = rateType;
    this.compoundingFrequency = compoundingFrequency;
    this.paymentAmountCents = paymentAmountCents;
    this.paymentFrequency = paymentFrequency;
    this.interestOnly = interestOnly;
  }

  public String getCustomerId() {
    return customerId;
  }

  public String getLoanId() {
    return loanId;
  }

  public LoanStatus getStatus() {
    return status;
  }

  public double getPrincipleCents() {
    return principleCents;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public int getPeriodMonths() {
    return periodMonths;
  }

  public int getTerm() {
    return term;
  }

  public Frequency getCompoundingFrequency() {
    return compoundingFrequency;
  }

  public double getInterestRate() {
    return interestRate;
  }

  public RateType getRateType() {
    return rateType;
  }

  public Frequency getPaymentFrequency() {
    return paymentFrequency;
  }

  public double getPaymentAmountCents() {
    return paymentAmountCents;
  }

  public boolean isInterestOnly() {
    return interestOnly;
  }

  public void setStatus(LoanStatus status) {
    this.status = status;
  }

  public void setCompoundingFrequency(Frequency compoundingFrequency) {
    this.compoundingFrequency = compoundingFrequency;
  }

  public void setPaymentFrequency(Frequency paymentFrequency) {
    this.paymentFrequency = paymentFrequency;
  }

  public void setIsInterestOnly(boolean interestOnly) {
    this.interestOnly = interestOnly;
  }

  public void setPaymentAmountCents(int paymentAmountCents) {
    this.paymentAmountCents = paymentAmountCents;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (o instanceof Loan) {
      Loan loan = (Loan) o;
      return loanId.equals(loan.getLoanId());
    }
    return false;
  }

  /** Checks if the loan is currently in the mainframe database or not. */
  @Override
  public boolean validate() {
    // Using nitrate mainframe connection
    Connection connection = Instance.getConnection();

    if (loanId == null || loanId.length() > 10 || loanId.length() == 0) {
      return false;
    }

    FindLoan findLoan = new FindLoan();
    findLoan.setId(loanId);

    return findLoan.send(connection).getWasSuccessful();
  }

  /** Adding new loan or updating existing loan in the mainframe */
  @Override
  public Loan persist() {
    // Using nitrate mainframe connection
    Connection connection = Instance.getConnection();

    // Setting up the loan message for mainframe
    UpdateLoan updateLoan = new UpdateLoan();
    if (loanId != null && loanId.length() <= 10) {
      updateLoan.setLoanId(loanId);
    } else return null;

    if (customerId != null) {
      updateLoan.setCustomerId(customerId);
    } else return null;

    if (principleCents > 0) {
      updateLoan.setPrincipal(principleCents);
    } else return null;

    if (interestRate > 0) {
      updateLoan.setRateValue(interestRate);
    } else return null;

    if (rateType != null) {
      updateLoan.setRateType(rateType);
    } else return null;

    if (startDate != null) {
      updateLoan.setStartDate(startDate);
    } else return null;

    if (periodMonths > 0) {
      updateLoan.setPeriod(periodMonths);
    } else return null;

    if (term > 0) {
      updateLoan.setTerm(term);
    } else return null;

    if (paymentAmountCents > 0) {
      updateLoan.setPaymentAmount(paymentAmountCents);
    } else return null;

    if (paymentFrequency != null) {
      updateLoan.setPaymentFrequency(paymentFrequency);
    } else return null;

    if (compoundingFrequency != null) {
      updateLoan.setCompounding(compoundingFrequency);
    } else return null;

    // Sending loan update message to the mainframe
    if (updateLoan.send(connection).getWasSuccessful()) {
      return this;
    } else return null;
  }

  @Override
  public void delete() {
    // Call mainframe to delete
  }

  /** Updates loan instance's fields to be consistent with mainframe */
  @Override
  public Loan get(String id) {
    // Using nitrate mainframe connection
    Connection connection = Instance.getConnection();

    LoadLoan loadloan = new LoadLoan();
    loadloan.setLoanId(id);

    // If the connection was not successful, not updating the instance and returning null
    if (loadloan.send(connection).getWasSuccessful()) {
      customerId = loadloan.getCustomerIdFromServer();
      status = LoanStatus.valueOf(loadloan.getStatusFromServer());
      principleCents = loadloan.getPrincipalFromServer();
      interestRate = loadloan.getRateValueFromServer();
      rateType = loadloan.getRateTypeFromServer();
      startDate = loadloan.getStartDateFromServer();
      periodMonths = loadloan.getPeriodFromServer();
      term = loadloan.getTermFromServer();
      paymentAmountCents = loadloan.getPaymentAmountFromServer();
      paymentFrequency = loadloan.getPaymentFrequencyFromServer();
      compoundingFrequency = loadloan.getCompoundingFromServer();
      return this;
    } else return null;
  }
}
