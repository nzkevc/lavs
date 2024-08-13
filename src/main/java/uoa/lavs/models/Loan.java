package uoa.lavs.models;

import java.util.Date;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;

public class Loan {

  private String id;
  private LoanStatus status;
  private int principleCents;
  private Date startDate;
  private int periodMonths;
  private double interestRate;
  private Frequency compoundingFrequency;
  private int paymentAmountCents;
  private Frequency paymentFrequency;
  private boolean interestOnly;

  public Loan(
      String id,
      int principleCents,
      Date startDate,
      int periodMonths,
      double interestRate,
      Frequency compoundingFrequency,
      int paymentAmountCents,
      Frequency paymentFrequency,
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

  public LoanStatus getStatus() {
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

  public Frequency getCompoundingFrequency() {
    return compoundingFrequency;
  }

  public double getInterestRate() {
    return interestRate;
  }

  public Frequency getPaymentFrequency() {
    return paymentFrequency;
  }

  public int getPaymentAmountCents() {
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
      return id.equals(loan.getId());
    }
    return false;
  }
}
