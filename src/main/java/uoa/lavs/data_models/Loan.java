package uoa.lavs.data_models;

import java.util.Date;

public class Loan {
  public enum Status {
    ACTIVE,
    INACTIVE,
    NEW,
    PENDING,
    CANCELLED,
  }

  public enum CompoundingFrequency {
    MONTHLY,
    QUARTERLY,
    SIX_MONTHLY,
    ANNUALLY,
  }

  public enum PaymentFrequency {
    MONTHLY,
    QUARTERLY,
    SIX_MONTHLY,
    ANNUALLY,
  }

  private String id;
  private Status status;
  private int principleCents;
  private Date startDate;
  private int periodMonths;
  private double interestRate;
  private CompoundingFrequency compoundingFrequency;
  private int paymentAmountCents;
  private PaymentFrequency paymentFrequency;
  private boolean interestOnly;

  public Loan(
      String id,
      int principleCents,
      Date startDate,
      int periodMonths,
      double interestRate,
      CompoundingFrequency compoundingFrequency,
      int paymentAmountCents,
      PaymentFrequency paymentFrequency,
      boolean interestOnly) {
    this.id = id;
    this.principleCents = principleCents;
    this.startDate = startDate;
    this.periodMonths = periodMonths;
    this.interestRate = interestRate;
    this.compoundingFrequency = compoundingFrequency;
    this.paymentAmountCents = paymentAmountCents;
    this.paymentFrequency = paymentFrequency;
    this.interestOnly = interestOnly;
  }

  public String getId() {
    return id;
  }

  public Status getStatus() {
    return status;
  }

  public int getPrincipleCents() {
    return principleCents;
  }

  public Date getStartDate() {
    return startDate;
  }

  public int getPeriodMonths() {
    return periodMonths;
  }

  public CompoundingFrequency getCompoundingFrequency() {
    return compoundingFrequency;
  }

  public double getInterestRate() {
    return interestRate;
  }

  public PaymentFrequency getPaymentFrequency() {
    return paymentFrequency;
  }

  public int getPaymentAmountCents() {
    return paymentAmountCents;
  }

  public boolean isInterestOnly() {
    return interestOnly;
  }

  public void setStatus(Status status) {
    if (!status.equals(Status.ACTIVE) && !status.equals(Status.INACTIVE)) {
      this.status = status;
    }
  }

  public void setCompoundingFrequency(CompoundingFrequency compoundingFrequency) {
    this.compoundingFrequency = compoundingFrequency;
  }

  public void setPaymentFrequency(PaymentFrequency paymentFrequency) {
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
      return id.equals(loan.getId());
    }
    return false;
  }
}
