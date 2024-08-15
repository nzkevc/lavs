package uoa.lavs.models;

import java.time.LocalDate;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;

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

  @Override
  public boolean validate() {
    return true;
  }
}
