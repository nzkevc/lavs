package uoa.lavs.models;

import java.time.LocalDate;
import uoa.lavs.mainframe.Frequency;
import uoa.lavs.mainframe.LoanStatus;
import uoa.lavs.mainframe.RateType;
import uoa.lavs.utils.ValidationUtils;
import uoa.lavs.utils.objects.ValidationException;

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

  /**
   * Validates the loanId. ONLY USE WHEN UPDATING A LOAN. DO NOT USE WHEN CREATING A NEW LOAN.
   *
   * @param loanId
   * @throws ValidationException
   */
  public static void validateLoanId(String loanId) throws ValidationException {
    ValidationUtils.validateFieldExists(loanId);
    if (loanId.length() > 14) {
      throw new ValidationException("Loan ID must be 14 characters or less.");
    }
  }

  public static void validateCustomerId(String customerId) throws ValidationException {
    ValidationUtils.validateFieldExists(customerId);
    Customer.validateCustomerId(customerId);
  }

  public static void validatePaymentFrequency(Frequency paymentFrequency)
      throws ValidationException {
    if (paymentFrequency == null) {
      throw new ValidationException("Payment frequency must be provided.");
    }
    if (!paymentFrequency.equals(Frequency.Monthly)
        && !paymentFrequency.equals(Frequency.Fortnightly)
        && !paymentFrequency.equals(Frequency.Weekly)) {
      throw new ValidationException("Payment frequency must be Monthly, Fortnightly or Weekly.");
    }
  }

  public static void validateCompoundingFrequency(Frequency compoundingFrequency)
      throws ValidationException {
    if (compoundingFrequency == null) {
      throw new ValidationException("Compounding frequency must be provided.");
    }
    if (!compoundingFrequency.equals(Frequency.Weekly)
        && !compoundingFrequency.equals(Frequency.Monthly)
        && !compoundingFrequency.equals(Frequency.Yearly)) {
      throw new ValidationException("Compounding frequency must be Weekly, Monthly or Yearly.");
    }
  }
}
