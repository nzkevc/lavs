package uoa.lavs.models;

import java.time.LocalDate;

public class LoanPaymentRow implements IModel {
  private final double paymentInterest;
  private final double paymentPrincipal;
  private final double paymentRemaining;
  private final int paymentNumber;
  private final LocalDate paymentDate;

  public LoanPaymentRow(
      double paymentInterest,
      double paymentPrincipal,
      double paymentRemaining,
      int paymentNumber,
      LocalDate paymentDate) {
    this.paymentInterest = paymentInterest;
    this.paymentPrincipal = paymentPrincipal;
    this.paymentRemaining = paymentRemaining;
    this.paymentNumber = paymentNumber;
    this.paymentDate = paymentDate;
  }

  public double getPaymentInterest() {
    return paymentInterest;
  }

  public double getPaymentPrincipal() {
    return paymentPrincipal;
  }

  public double getPaymentRemaining() {
    return paymentRemaining;
  }

  public int getPaymentNumber() {
    return paymentNumber;
  }

  public LocalDate getPaymentDate() {
    return paymentDate;
  }
}
