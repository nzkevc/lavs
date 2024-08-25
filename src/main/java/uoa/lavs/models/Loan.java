package uoa.lavs.models;

import java.time.LocalDate;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;

public class Loan implements IModel {

  private String customerId;
  private String loanId;
  private String customerName;
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
  private LoanPayments loanPayments;
  private LoanSummary loanSummary;

  public Loan() {}

  public Loan(String loanId) {
    this.loanId = loanId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getLoanId() {
    return loanId;
  }

  public void setLoanId(String loanId) {
    this.loanId = loanId;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public LoanStatus getStatus() {
    return status;
  }

  public void setStatus(LoanStatus status) {
    this.status = status;
  }

  public void setStatus(String status) {
    this.status = LoanStatus.valueOf(status);
  }

  public double getPrincipleCents() {
    return principleCents;
  }

  public void setPrincipleCents(double principleCents) {
    this.principleCents = principleCents;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public int getPeriodMonths() {
    return periodMonths;
  }

  public void setPeriodMonths(int periodMonths) {
    this.periodMonths = periodMonths;
  }

  public int getTerm() {
    return term;
  }

  public void setTerm(int term) {
    this.term = term;
  }

  public Frequency getCompoundingFrequency() {
    return compoundingFrequency;
  }

  public void setCompoundingFrequency(Frequency compoundingFrequency) {
    this.compoundingFrequency = compoundingFrequency;
  }

  public double getInterestRate() {
    return interestRate;
  }

  public void setInterestRate(double interestRate) {
    this.interestRate = interestRate;
  }

  public RateType getRateType() {
    return rateType;
  }

  public void setRateType(RateType rateType) {
    this.rateType = rateType;
  }

  public Frequency getPaymentFrequency() {
    return paymentFrequency;
  }

  public void setPaymentFrequency(Frequency paymentFrequency) {
    this.paymentFrequency = paymentFrequency;
  }

  public double getPaymentAmountCents() {
    return paymentAmountCents;
  }

  public void setPaymentAmountCents(double paymentAmountCents) {
    this.paymentAmountCents = paymentAmountCents;
  }

  public boolean isInterestOnly() {
    return interestOnly;
  }

  public void setInterestOnly(boolean interestOnly) {
    this.interestOnly = interestOnly;
  }

  public LoanPayments getLoanPayments() {
    return loanPayments;
  }

  public void setLoanPayments(LoanPayments loanPayments) {
    this.loanPayments = loanPayments;
  }

  public LoanSummary getLoanSummary() {
    return loanSummary;
  }

  public void setLoanSummary(LoanSummary loanSummary) {
    this.loanSummary = loanSummary;
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

  public boolean validateLoanId(String loanId) {
    return loanId.length() <= 14 && !loanId.isEmpty();
  }

  public boolean validateCustomerId(String customerId) {
    if (customerId == null || customerId.isEmpty()) {
      return false;
    }
    return Customer.validateCustomerId(customerId);
  }

  public boolean validatePaymentFrequency(Frequency paymentFrequency) {
    if (paymentFrequency == null) {
      return false;
    }
    return paymentFrequency.equals(Frequency.Monthly)
        || paymentFrequency.equals(Frequency.Fortnightly)
        || paymentFrequency.equals(Frequency.Weekly);
  }

  public boolean validateCompoundingFrequency(Frequency compoundingFrequency) {
    if (compoundingFrequency == null) {
      return false;
    }
    return compoundingFrequency.equals(Frequency.Weekly)
        || compoundingFrequency.equals(Frequency.Monthly)
        || compoundingFrequency.equals(Frequency.Yearly);
  }
}
