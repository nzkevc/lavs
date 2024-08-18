package uoa.lavs.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;

public class Loan implements IModel<Loan> {

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
  private List<Integer> paymentNumbers;
  private List<Double> paymentInterests;
  private List<Double> paymentPrincipals;
  private List<Double> paymentRemainings;
  private LoanSummary loanSummary;

  public Loan(String loanId) {
    this.loanId = loanId;
    paymentNumbers = new ArrayList<>();
    paymentInterests = new ArrayList<>();
    paymentPrincipals = new ArrayList<>();
    paymentRemainings = new ArrayList<>();
  }

  public Loan(
      String customerId,
      String loanId,
      String customerName,
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
    this.customerName = customerName;
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
    paymentNumbers = new ArrayList<>();
    paymentInterests = new ArrayList<>();
    paymentPrincipals = new ArrayList<>();
    paymentRemainings = new ArrayList<>();
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

  public void setStatus(LoanStatus status) {
    this.status = status;
  }

  public List<Integer> getPaymentNumbers() {
    return paymentNumbers;
  }

  public void setPaymentNumbers(List<Integer> paymentNumbers) {
    this.paymentNumbers = paymentNumbers;
  }

  public List<Double> getPaymentInterests() {
    return paymentInterests;
  }

  public void setPaymentInterests(List<Double> paymentInterests) {
    this.paymentInterests = paymentInterests;
  }

  public List<Double> getPaymentPrincipals() {
    return paymentPrincipals;
  }

  public void setPaymentPrincipals(List<Double> paymentPrincipals) {
    this.paymentPrincipals = paymentPrincipals;
  }

  public List<Double> getPaymentRemainings() {
    return paymentRemainings;
  }

  public void setPaymentRemainings(List<Double> paymentRemainings) {
    this.paymentRemainings = paymentRemainings;
  }

  public void addPayment(Integer number, double interest, double principal, double remaining) {
    paymentNumbers.add(number);
    paymentInterests.add(interest);
    paymentPrincipals.add(principal);
    paymentRemainings.add(remaining);
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

  // TODO
  @Override
  public boolean validate() {
    return true;
  }
}
