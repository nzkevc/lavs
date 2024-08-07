package uoa.lavs.data_models;

import java.util.Date;

public class Loan {
  private String id;
  // status
  private int principleCents;
  private Date startDate;
  private int periodMonths;
  private double interestRate;
  // compounding frequency
  private int paymentAmountCents;
  // payment frequency
  private boolean interestOnly;

  public Loan(
      String id,
      int principleCents,
      Date startDate,
      int periodMonths,
      double interestRate,
      int paymentAmountCents,
      boolean interestOnly) {
    this.id = id;
    this.principleCents = principleCents;
    this.startDate = startDate;
    this.periodMonths = periodMonths;
    this.interestRate = interestRate;
    this.paymentAmountCents = paymentAmountCents;
    this.interestOnly = interestOnly;
  }

  public String getId() {
    return id;
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

  public double getInterestRate() {
    return interestRate;
  }

  public int getPaymentAmountCents() {
    return paymentAmountCents;
  }

  public boolean isInterestOnly() {
    return interestOnly;
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
